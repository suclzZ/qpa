package com.app.qpa.element;

/**
 *
 * 稽核规则(条件)
 *
 */
public interface Rule extends QpaModal{

	/**
	 *  规则源属性，只能为属性
	 * @return
	 */
	String getSource();

	/**
	 * 比较方式，比如：> >= < <= IN BWT等
	 * @return
	 */
	String getOperate();

	/**
	 *  目标属性，可以有多种类型，具体与TargetType对应
	 * @return
	 */
	String getTarget();

	/**
	 * 01 常量 ;
	 * 02 属性;
	 * 03 bool表达式(代表该规则的结果);
	 * 04 表达式（代表target的值）;
	 * 05 正则表达式
	 *
	 * @return
	 */
	String getTargetType();


	/**
	 * 上级规则，若某个规则有上级规则，则忽略其结果，以最终上级结果为准
	 * @return
	 */
	Rule getParentRule();

	/**
	 * 当存在parentRule时有效，明确子级规则关系，最终计算出规则结果
	 * 比如 || &&
	 * @return
	 */
	String ruleRelate();
}
