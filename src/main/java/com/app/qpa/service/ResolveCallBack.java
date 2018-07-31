package com.app.qpa.service;

import java.util.List;

import com.app.qpa.element.AuditMessage;

/**
 * 稽核规则处理后的回调处理
 * @param <T>
 */
public interface ResolveCallBack<T> {

	public void call(T t) ;
}
