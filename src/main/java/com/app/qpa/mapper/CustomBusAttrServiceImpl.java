package com.app.qpa.mapper;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
@Component
public class CustomBusAttrServiceImpl implements CustomBusAttrService{
    private static final Map<String,String> stcMap = new HashMap<String,String>();

    static {
        stcMap.put("now", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    @Override
    public String getValue(String property) {
        return stcMap.get(property);
    }
}
