package com.app.qpa.source;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

public abstract class AbstractSourceData implements SourceData{

	@Override
	public boolean hasProperty(String attrName) {
		Field field = ReflectionUtils.findField(this.getBeanClass(), attrName);
		return field!=null;
	}
	
	public abstract Class<? extends SourceData> getBeanClass();
}
