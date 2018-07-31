package com.app.qpa.service;

import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.source.SourceData;

import java.util.List;
import java.util.Map;

/**
 * 异常数据记录
 *
 */
public interface AuditQuestionableRecordService {

    void save(SourceData sourceData, Item item, Map<Rule, List<Rule>> map);

}
