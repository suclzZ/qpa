package com.app.qpa.strategy;

import java.util.List;

import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.service.ResolveCallBack;
import com.app.qpa.source.SourceData;

/**
 * 稽核规则处理结构
 */
public interface RuleStragegy {

	/**
	 * 规则校验策略
     * @param item
     * @param rules
     * @param data
     */
	public boolean process(Item item, List<Rule> rules, SourceData data);

	/**
	 *
	 * @param item
	 * @param rules
	 * @param data
	 * @param resolveCallBack 该接口返回不通过的规则
	 */
	public boolean process(Item item, List<Rule> rules, SourceData data, ResolveCallBack resolveCallBack);
}
