package com.app.qpa.entity;

import com.app.qpa.mapper.BusAttr;
import lombok.Data;

/**
 *
 *
 */
@Data
public class AtAttr implements BusAttr{
    private String code;
    private String caption;
    private String type;
    private String value;
    private String valueType;

    @Override
    public String getName() {
        return this.code;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getValueType() {
        return this.valueType;
    }
}
