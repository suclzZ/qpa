package com.app.qpa.core;

import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.element.Type;
import com.app.qpa.mapper.PropertyConverter;
import com.app.qpa.resolver.RuleResolver;
import com.app.qpa.service.*;
import com.app.qpa.source.SourceData;
import com.app.qpa.strategy.AndMapperRuleStrategy;
import com.app.qpa.strategy.RuleStragegy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 稽核引擎
 *
 */
@Component
public class AuditEngine {
	private static Logger logger = LoggerFactory.getLogger(AuditEngine.class);
	@Autowired(required = false)
	private AuditRecordService auditRecordService;
	@Autowired(required = false)
	private AuditQuestionableRecordService auditQuestionableRecordService;
	@Autowired
	private PropertyConverter propertyConverter;
	@Autowired
	private ValidateFactory validateFactory;

	/**
	 * res,info
	 * @param types 稽核类型
	 * @param sourceDatas 稽核对象
	 */
	public void audit(List<? extends Type> types , List<? extends SourceData> sourceDatas) {
		if(!CollectionUtils.isEmpty(types) && !CollectionUtils.isEmpty(sourceDatas)) {
			for(SourceData  sourceData :sourceDatas) {//
				checkSourceDataByType(types , sourceData);
			}
		}
	}

	/**
	 * @param types
	 * @param sourceData
	 * @return
	 */
	public void checkSourceDataByType(List<? extends Type> types, SourceData sourceData) {
		logger.debug("检验数据{}中....", sourceData);
		if(!CollectionUtils.isEmpty(types) && sourceData!=null) {
			for(Type type :types){
				List<? extends Item> items = type.getItems();
				checkSourceDataByItem(type, items , sourceData);
			}
		}
	}

	/**
	 * 根据稽核组开始稽核
	 * @param type
	 * @param items
	 * @param sourceData
	 */
	public void checkSourceDataByItem(Type type, List<? extends Item> items ,SourceData sourceData){
		if(!CollectionUtils.isEmpty(items) && sourceData!=null) {
			for(Item item :items){
				if(!validateItem(item)){
					continue;
				}
				List<? extends Rule> rules = item.getRules();
				checkSourceDataByRule(item, rules , sourceData);
			}
		}
	}

	/**
	 * 校验item是否需要处理
	 * @param item
	 * @return
	 */
	private boolean validateItem(Item item) {
		List<Validator> validators = this.validateFactory.getValidators();
		boolean need = true;
		if(validators!=null){
			for(Validator validator : validators){
				if(validator.getType() == item.getClass() && validator.validate(item)){
					//
				}else{
					need = false;
					break;
				}
			}
		}
		return need;
	}

	/**
	 * 根据稽核规则开始稽核
	 * @param item
	 * @param rules
	 * @param sourceData
	 */
	public void checkSourceDataByRule(Item item , List<? extends Rule> rules,SourceData sourceData){
		if(!CollectionUtils.isEmpty(rules) && sourceData!=null){
			RuleStragegy ruleStragegy = new AndMapperRuleStrategy(propertyConverter);
			RuleResolver ruleResolver = new RuleResolver(ruleStragegy);
			recordData(sourceData,AuditRecordService.STATUS_01);
			boolean result = ruleResolver.resolve(item, (List<Rule>) rules, sourceData, new ResolveCallBack<Map<Rule,List<Rule>>>() {
				@Override
				public void call(Map<Rule,List<Rule>> map) {
					logger.error("---------------------------稽核失败数据{},对应规则{}",sourceData.sourceId(),map);
					//不通过的规则
					if(auditQuestionableRecordService!=null){
						auditQuestionableRecordService.save(sourceData ,item , map);
					}
				}
			});
			if(!result){
				logger.error("+++++++++++++++++++++++失败{}",sourceData);
				recordData(sourceData,AuditRecordService.STATUS_02_1);
			}else{
				recordData(sourceData,AuditRecordService.STATUS_02);
			}
		}
	}

	/**
	 * 记录稽核数据状态
	 * @param sourceData
	 * @param status
	 */
	private void recordData(SourceData sourceData,String status){
		if(auditRecordService!=null)
			auditRecordService.save(sourceData,status);
	}
}
