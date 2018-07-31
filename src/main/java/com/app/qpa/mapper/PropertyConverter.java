package com.app.qpa.mapper;

import com.app.qpa.source.SourceData;

import java.util.List;
import java.util.Map;

/**
 * 属性转换成具体值
 * 根据SourceData类型找到对应的BusAttr，任何根据attr与property对于
 * 计算出该属性的具体值
 *
 */
public interface PropertyConverter {

    String convert(SourceData data , String property);

    Map<String,String> convert(SourceData data , List<String> variables);

    String getValueType(SourceData data,String property);
}
