package com.app.qpa.resolver;

import java.util.List;

import com.app.qpa.element.AuditMessage;
import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.service.ResolveCallBack;
import com.app.qpa.source.SourceData;
import com.app.qpa.strategy.RuleStragegy;

/**
 * 稽核规则处理形式
 * 具体实现由ruleStragegy完成
 *
 */
public class RuleResolver {
	private RuleStragegy ruleStragegy;
	
	public RuleResolver(RuleStragegy ruleStragegy) {
		this.ruleStragegy = ruleStragegy;
	}

	public boolean resolve(Item item, List<Rule> rules, SourceData data , ResolveCallBack callBack) {
		if(ruleStragegy!=null){
			return ruleStragegy.process(item , rules , data, callBack);
		}
		return true;
	}

	public boolean resolve(Item item, List<Rule> rules, SourceData data) {
		return this.resolve(item,rules,data,null);
	}

}
