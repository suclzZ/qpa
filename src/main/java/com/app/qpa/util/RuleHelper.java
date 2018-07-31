package com.app.qpa.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

/**
 *具体稽核规则处理定义
 *
 */
public class RuleHelper {

	public static boolean operate(String source, String operate, String target) {
//		if(ValidateUtils.isDate(source) || ValidateUtils.isDate(target)){
//			return dateOperate(source, operate, target);
//		}
		//operate: = > >= < <= in 
		if(NumberUtils.isNumber(source) && converToNumber(target)!=null) {
			double sourcev = Double.valueOf(source);
			Double[] targetv = converToNumber(target);
			if("=".equals(operate)) {
				return sourcev == targetv[0] ;
			}else if(">".equals(operate)) {
				return sourcev > targetv[0] ;
			}else if(">=".equals(operate)) {
				return sourcev >= targetv[0] ;
			}else if("<".equals(operate)) {
				return sourcev < targetv[0] ;
			}else if("<=".equals(operate)) {
				return sourcev <= targetv[0] ;
			}else if("BTW".equals(operate)) {
				return targetv.length==2 && sourcev >= targetv[0] && sourcev <= targetv[1] ;
			}else if("IN".equals(operate)) {
				return Arrays.asList(targetv).contains(sourcev) ;
			}
		}
		return false;
	}

	/**
	 * 日期比对规则
	 * @param source
	 * @param operate
	 * @param target
	 * @return
	 */
	private static boolean dateOperate(String source, String operate, String target) {
		if("=".equals(operate)) {

		}else if(">".equals(operate)) {

		}else if(">=".equals(operate)) {

		}else if("<".equals(operate)) {

		}else if("<=".equals(operate)) {

		}
		return true;
	}

	/**
	 * 字符串转换double数组
	 * @param value
	 * @return
	 */
	public static Double[] converToNumber(String value) {
		List<Double> vlist = new ArrayList<Double>();
		if(!StringUtils.isEmpty(value)) {
			if(value.indexOf(",")!=-1) {
				String[] values = value.split(",");
				boolean isNum = true;
				for(String v : values){
					boolean is = NumberUtils.isNumber(v);
					if(is) {
						vlist.add(Double.valueOf(v));
					}else {
						isNum = false;
					}
				}
				if(isNum) {//发现不是数字 立即返回
			//		return vlist.toArray(new Double[vlist.size()]);
				}
			}else {
				vlist.add( Double.valueOf(value) );
			}
		}
		return vlist.toArray(new Double[vlist.size()]);
	}
	
	static interface Operate{
		public static final String EQ="=";
		public static final String LT="<";
		public static final String GT=">";
		public static final String LE="<=";
		public static final String GE=">=";
		public static final String BWT="BWT";
		public static final String IN="IN";
		
	}
}
