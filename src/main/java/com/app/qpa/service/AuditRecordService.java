package com.app.qpa.service;

import com.app.qpa.source.SourceData;

/**
 * 数据稽核记录
 *
 */
public interface AuditRecordService {
    String STATUS_01 = "01";//开始
    String STATUS_02 = "02";//完成
    String STATUS_02_1 = "03";//不通过

    void save(SourceData data , String status);
}
