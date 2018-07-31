package com.app.qpa.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.nfunk.jep.JEP;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据值的一些处理
 */
public class ValueUtils {

	/**
	 *  根据属性从bean取值
	 * @param bean
	 * @param property
	 * @return
	 */
	public static String valueFromBean(Object bean , String property) {
		Object beanValue = null;
		try {
			beanValue = PropertyUtils.getProperty(bean, property);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return value(beanValue, null);
	}

	/**
	 * 获取表达式的所有变量
	 *  + - * /
	 * @param fn
	 * @return
	 */
	public static List<String> findVarFromFn(String fn) {
		return findExpression(fn,"\\(|\\)|\\+|\\-|\\*|\\/");
	}

	/**
	 * 所有比较函数
	 * > >= < <=
	 * @param fn
	 * @return
	 */
	public static List<String> findExpVarFromFn(String fn) {
		return findExpression(fn,"\\>|\\>\\=|\\<|\\<\\=|\\!");
	}

	public static List<String> findAllVarFromFn(String fn) {
		return findExpression(fn,"\\(|\\)|\\+|\\-|\\*|\\/|\\>|\\>\\=|\\<|\\<\\=|\\!");
	}

	public static List<String> findExpression(String fn , String reg){
		String[] vals = fn.split(reg);
		List<String> valList = new ArrayList<String>(vals.length);
		for(String val : vals){
			if(val!=null){
				if(!"".equals((val = val.trim()))){
					valList.add(val);
				}
			}
		}
		return valList;
	}

	/**
	 * 对象转换字符串
	 * @param obj
	 * @param defaultval
	 * @return
	 */
	public static String value(Object obj ,String defaultval) {
		if(StringUtils.isEmpty(obj)) {
			return defaultval;
		}else
			return obj.toString().trim();
	}

	/**
	 * 将对象转存成boolean
	 * @param value
	 * @return
	 */
	public static boolean valueOfBool(Object value) {
		if(StringUtils.isEmpty(value)) return false;
		if(value instanceof String) {
			if(NumberUtils.isNumber((String) value)) {
				return Double.valueOf((String) value)>0;
			}
			return Boolean.valueOf((String) value);
		}else{
			return false;
		}
	}

	public static String convert(String info ,String... params){
		Pattern pattern = Pattern.compile("\\{[^}]\\}");
		Matcher matcher = pattern.matcher(info);
		int index = 0;
		StringBuffer sb = new StringBuffer();
		while (matcher.find()){
			if(params.length>=index){
				throw new ArrayIndexOutOfBoundsException("下标越界");
			}
			matcher.appendReplacement(sb,params[index++]);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
