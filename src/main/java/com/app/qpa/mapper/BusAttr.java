package com.app.qpa.mapper;

/**
 * 稽核属性定义：
 *	一般情况，稽核属性定义了源数据对应的属性（与class定义相同）
 *	或者直接定义常量
 *	货值定义的是公式
 *
 */
public interface BusAttr {

	/**
	 * 属性名称 code
	 * @return
	 */
	String getName();//属性名称

	/**
	 * 属性类型
	 * 1-字段；2-公式; 3-常数
	 * @return
	 */
	String getType();

	/**
	 * 属性值
	 * 根据属性类型进行处理
	 * @return
	 */
	String getValue();

	/**
	 * 在稽核阶段用到
	 * 决定计算方式 01：数字、02：文本:03：日期...
	 * @return
	 */
	String getValueType();
	
}
