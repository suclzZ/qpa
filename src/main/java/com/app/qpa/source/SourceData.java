package com.app.qpa.source;

/**
 * 稽核数据源
 *
 */
public interface SourceData {

	boolean hasProperty(String attrName);
	
	String sourceId();
	
}
