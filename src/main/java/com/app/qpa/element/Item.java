package com.app.qpa.element;

import java.util.List;

/**
 * 稽核项
 *
 */
public interface Item extends QpaModal{

	/**
	 *  组包含的所有规则
	 * @return
	 */
	List<? extends Rule> getRules();

	/**
	 *  规则关系，默认情况子级规则必须全部成立，即&&
	 *  子级规则指 getRules 中parentRule为null的
	 *  && ||
	 * @return
	 */
	String getLoginc();

}
