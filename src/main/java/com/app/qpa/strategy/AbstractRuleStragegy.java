package com.app.qpa.strategy;

import com.app.qpa.element.Rule;
import com.app.qpa.eo.FnProcessor;
import com.app.qpa.exception.QpaException;
import com.app.qpa.mapper.PropertyConverter;
import com.app.qpa.mapper.PropertyConverterSupport;
import com.app.qpa.source.SourceData;
import com.app.qpa.util.RuleHelper;
import com.app.qpa.util.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定义抽闲规则稽核逻辑
 * 
 *
 */
public abstract class AbstractRuleStragegy implements RuleStragegy{
	private static Logger logger = LoggerFactory.getLogger(AbstractRuleStragegy.class);

	public static final String CONSTANT = "01";//常量
	public static final String VARIABLE = "02";//属性变量
	public static final String EXPRESSION = "03";//表达式，运算结果为规则最终结果
	public static final String VALUEEXPR = "04";//表达式，运算结果为target的结果
	public static final String REGEX = "05";//正则表达式

	/**
	 * 将rule进行解析对应有3个属性
	 *  source:只能从attr中取得{@link com.app.qpa.mapper.BusAttr}
	 *  operate:计算方式，可参考{@link RuleHelper.Operate}
	 *  target:可以有以上5中方式构造
	 *
	 * @param rule
	 * @param data
	 * @param propertyConverter
	 * @return
	 */
	public boolean checkRule(Rule rule, SourceData data , PropertyConverter propertyConverter){
		if(rule==null || data == null || propertyConverter==null){
			return true;
		}else{
			boolean res = true,dateOpt = false;
			//目前规则的左边限定从数据源中取
			String sourceProp = rule.getSource(),sourceVal = propertyConverter.convert(data,sourceProp);
			String targetProp = rule.getTarget(),targetType = rule.getTargetType(),tarvetVal;
			if(sourceVal==null && !EXPRESSION.equals(targetType)){
				throw new QpaException("源数据"+sourceProp+"对应的值不能为空");
			}
			//日期运算不支持的target类型
			if(PropertyConverterSupport.VALUE_TYPE_DATE.equals(propertyConverter.getValueType(data,sourceProp))){
				dateOpt = true;
				if(REGEX.equals(targetType)){
					throw new QpaException("规则targetType对应类型不支持日期操作");
				}
			}
			if(CONSTANT.equals(targetType)) {
				res = RuleHelper.operate(sourceVal, rule.getOperate(), targetProp) ;
			}else if(VARIABLE.equals(targetType)){//attr里的属性
				tarvetVal = propertyConverter.convert(data , targetProp);
				res = RuleHelper.operate(sourceVal, rule.getOperate(), tarvetVal) ;
			}else if(EXPRESSION.equals(targetType)){//source可以为null
				Map<String,String> variables = propertyConverter.convert(data, ValueUtils.findAllVarFromFn(targetProp));
				res = ValueUtils.valueOfBool( FnProcessor.exec(targetProp,variables,dateOpt?FnProcessor.ProcessorType.DATE:null) );
			}else if(VALUEEXPR.equals(targetType)) {
				Map<String,String> variables = propertyConverter.convert(data, ValueUtils.findVarFromFn(targetProp));
				res = RuleHelper.operate(sourceVal, rule.getOperate(), FnProcessor.exec(targetProp,variables,dateOpt?FnProcessor.ProcessorType.DATE:null)) ;
			}else if(REGEX.equals(targetType)) {
				Matcher matcher = Pattern.compile(targetProp).matcher(sourceVal);
				res = matcher.matches();
			}
			return res;
		}
	}

}
