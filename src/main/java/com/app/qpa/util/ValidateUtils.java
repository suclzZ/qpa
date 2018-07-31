package com.app.qpa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.qpa.element.Item;
import org.springframework.util.StringUtils;

/**
 * 各种校验工具
 */
public class ValidateUtils {

	/**
	 * 日期校验判断指定日期是否在startData与endDate之间
	 * @param refDate
	 * @param startData
	 * @param endDate
	 * @return
	 */
	public static boolean isDateValid(Date refDate, String startData, String endDate) {
		boolean result = true;
		if(refDate==null) {
			refDate = new Date();
		}
		if(!StringUtils.isEmpty(startData)) {
			result =  refDate.after(formateDate(startData,null));
		}
		if(!StringUtils.isEmpty(endDate)){
			result =  refDate.before(formateDate(endDate,null));
		}
		return result;
	}
	
	private static Date formateDate(String dataStr, String formate) {
		formate = formate==null?"yyyy-MM-dd":formate;
		try {
			return new SimpleDateFormat(formate).parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断是否为日期，目前仅支持yyyy-mm-dd yyyy/mm/dd  12位
	 *
	 * @param dateStr
	 * @return
	 */
	public static boolean isDate(String dateStr){
		if(StringUtils.isEmpty(dateStr) || dateStr.length()<12) return false;
		if(dateStr.length()>=12){
			String newDateStr = dateStr.substring(0, 12);
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(newDateStr);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				try {
					Date date = new SimpleDateFormat("yyyy/MM/dd").parse(newDateStr);
					return true;
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	public static Date hasDate(String dateStr){
		if(StringUtils.isEmpty(dateStr) || dateStr.length()<10) return null;
		if(dateStr.length()>=10){
			String newDateStr = dateStr.substring(0, 10);
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(newDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
				try {
					date = new SimpleDateFormat("yyyy/MM/dd").parse(newDateStr);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			return date;
		}
		return null;
	}
	
}
