package com.app.qpa.entity;

import com.app.qpa.element.Rule;
import lombok.Data;

@Data
public class AtRule implements Rule{
	private String id;
	private String name;
	private String source;
	private String operate;
	private String target;
	private String targetType;//
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getCode() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean valid() {
		return true;
	}

	@Override
	public String getSource() {
		return this.source;
	}

	@Override
	public String getOperate() {
		return this.operate;
	}

	@Override
	public String getTarget() {
		return this.target;
	}

	@Override
	public String getTargetType() {
		return this.targetType;
	}

	@Override
	public Rule getParentRule() {
		return null;
	}

	@Override
	public String ruleRelate() {
		return null;
	}

}
