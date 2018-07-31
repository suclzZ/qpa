package com.app.qpa.strategy;

import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.mapper.PropertyConverter;
import com.app.qpa.service.ResolveCallBack;
import com.app.qpa.source.SourceData;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class AndMapperRuleStrategy extends AbstractRuleStragegy{
	private PropertyConverter propertyConverter;

	public AndMapperRuleStrategy(PropertyConverter propertyConverter){
		this.propertyConverter = propertyConverter;
	}

	@Override
	public boolean process(Item item, List<Rule> rules, SourceData data) {
		return process(item , rules , data, null);
	}

	/**
	 * 稽核规则的处理实现
	 * @param item
	 * @param rules
	 * @param data
	 * @param resolveCallBack
	 */
	@Override
	public boolean process(Item item, List<Rule> rules, SourceData data, ResolveCallBack resolveCallBack) {
		if(!CollectionUtils.isEmpty(rules) && data !=null){
			List<Boolean> pruleRes = new ArrayList<Boolean>(); // 规则校验结果稽核
			Map<Rule,List<Rule>> unPassruleMap = new HashMap<Rule,List<Rule>>();//校验失败记录
			//转换成prule:rules格式
			Map<Rule,List<Rule>> ruleMap = convertListToMap(rules);//Rule需要实现hash equal
			if(!CollectionUtils.isEmpty(ruleMap)){
				//真正校验的是map的key
				for(Map.Entry<Rule,List<Rule>> entry : ruleMap.entrySet()){
					Rule prule = entry.getKey();//需要校验的规则
					List<Rule> crules = entry.getValue(); //key对应的所有子级
					if(!CollectionUtils.isEmpty(crules)){ //有子级
						String relate = prule.ruleRelate(); //子级运算关系
						List<Boolean> cruleRes = new ArrayList<Boolean>();
						for(Rule crule : crules){
							cruleRes.add( checkRule(crule , data , propertyConverter) );
						}
						boolean res = isTrue4List(relate,cruleRes);//父级运算方式
						if(!res){//校验失败的rule
							unPassruleMap.put(prule, crules);//如果是子级，具体是哪个rule不通过没有管，可以细化
						}
						pruleRes.add(res);
					}else{ // 没子级
						boolean res = checkRule(prule,data,propertyConverter);
						if(!res){
							unPassruleMap.put(prule, crules);
						}
						pruleRes.add(res);
					}
				}
			}
			String ruleLogic = item.getLoginc();
			boolean result = isTrue4List(ruleLogic, pruleRes); //最终结果
			if(resolveCallBack!=null && !result){
				resolveCallBack.call(unPassruleMap);
			}
			return result;
		}
		return true;
	}

	/**
	 * Boolean集合是否为真进行判断
	 * && 全部为真，|| 有一个为真
	 * @param logic
	 * @param res || &&
	 * @return
	 */
	private boolean isTrue4List(String logic, List<Boolean> res) {
		if("||".equals(logic)) {
			for(boolean r : res) {
				if(r) return true;
			}
			return false;
		}else if("&&".equals(logic)) {
			for(boolean r : res) {
				if(!r) return false;
			}
			return true;
		}else {
			//
		}
		return true;
	}

	/**
	 * key 为父级规则 会定义子级规则的运算逻辑
	 * value 为子级规则
	 * 在实际运算时都是算父级规则
	 * 目前一级关系，涉及父及的逻辑关系在稽核项中定义
	 * @param rules
	 * @return
	 */
	private Map<Rule, List<Rule>> convertListToMap(List<Rule> rules) {
		Map<Rule, List<Rule>> ruleMap = new HashMap<Rule, List<Rule>>();
		if(rules!=null && rules.size()>0) {
			for(Rule rule :rules) {
				if(rule.getParentRule()!=null) {
					List<Rule> rs = ruleMap.get(rule.getParentRule());
					if(rs!=null) {
						rs.add(rule);
					}else {
						List<Rule> ar = new ArrayList<Rule>();
						ar.add(rule);
						ruleMap.put(rule.getParentRule(), ar);
					}
				}else {
					if(!ruleMap.keySet().contains(rule))
						ruleMap.put(rule, null);
				}
			}
		}
		return ruleMap;
	}

}
