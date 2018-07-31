package com.app.qpa.service;

/**
 * 校验接口
 *
 */
public interface Validator<T> {

    /**
     * 检验
     * @param t
     * @return
     */
    boolean validate(T t);

    /**
     * 校验对象的class类型
     * @return
     */
    Class getType();
}
