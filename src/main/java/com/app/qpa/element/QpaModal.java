package com.app.qpa.element;

/**
 * 稽核属性（类型、组、规则）需要实现的接口
 */
public interface QpaModal {

	/**
	 * 唯一标识
	 * @return
	 */
	String getId();

	/**
	 * 编码 同id
	 * @return
	 */
	String getCode();

	/**
	 * 说明
	 * @return
	 */
	String getName();

	/**
	 * 是否有效
	 * @return
	 */
	boolean valid();
}
