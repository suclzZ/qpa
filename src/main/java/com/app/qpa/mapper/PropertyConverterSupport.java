package com.app.qpa.mapper;

import com.app.qpa.eo.FnProcessor;
import com.app.qpa.exception.QpaException;
import com.app.qpa.source.SourceData;
import com.app.qpa.util.ValueUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性转换的基础实现，可以继承该类完成进行补充
 *
 */
public abstract class PropertyConverterSupport implements PropertyConverter{
    private Logger logger = LoggerFactory.getLogger(PropertyConverterSupport.class);
    public static final String TYPE_ATTR  = "1";
    public static final String TYPE_FN  = "2";
    public static final String TYPE_CONSTANT  = "3";

    public static final String VALUE_TYPE_NUMBER  = "01";
    public static final String VALUE_TYPE_STRING  = "02";//default
    public static final String VALUE_TYPE_DATE  = "03";
    public static final String VALUE_TYPE_EMPTY  = "";

    @Autowired(required = false)
    private CustomBusAttrService customBusAttrService;

    public String convert(SourceData data, String property){
        if(customBusAttrService!=null){
            String value = customBusAttrService.getValue(property);
            if(StringUtils.isNotEmpty(value)) return value;
        }
        List<? extends BusAttr> attrs =  getBusAttrs(data);
        if(attrs!=null){
            BusAttr attr ;
            if((attr = propInAttrs(attrs,property))!=null){
                String attrValue = attr.getValue(),attrType = attr.getType();
                if(VALUE_TYPE_DATE.equals(attr.getValueType()) && TYPE_FN.equals(attrType)){
                    throw  new QpaException("日期类型属性不支持公式计算！");
                }
                if(TYPE_ATTR.equals(attrType)){ //属性
                    return ValueUtils.valueFromBean(data , property);
                }else if(TYPE_FN.equals(attrType)){
                    String fn = attr.getValue();
                    Map<String,String> variables = convert(data, ValueUtils.findVarFromFn(fn));
                    return FnProcessor.exec(fn,variables) ;
                }else if(TYPE_CONSTANT.equals(attrType)){
                    return attr.getValue();
                }else{
                    throw  new QpaException("不合法属性类型 "+property);
                }
            }else{
                logger.info("在源数据{}中找不到属性{}",data.getClass(),property);
            }
        }
        return "";
    }

    /**
     *
     * 将变量转换成对应的值
     * 注意：数字不做处理，比如1、01
     * @param data
     * @param variables
     * @return
     */
    public Map<String,String> convert(SourceData data, List<String> variables){
        Map<String,String> varMap = new HashMap<String,String>();
        if(variables!=null){
            for(String var : variables){
                if(NumberUtils.isNumber(var)){
            //        varMap.put(var,var);//String .valueOf(NumberUtils.createDouble(var))
                }else{
                    varMap.put(var,convert(data,var));
                }
            }
        }
        return varMap;
    }

    private BusAttr propInAttrs(List<? extends BusAttr> attrs,String prop){
        if(attrs==null || StringUtils.isEmpty(prop)) return null;
        for(BusAttr attr :attrs){
            if(prop.equals(attr.getName())){
                return attr;
            }
        }
        return null;
    }

    /**
     * 根据SourceData找到对应的Attr集合
     * @param data
     * @return
     */
    protected abstract List<? extends BusAttr>  getBusAttrs(SourceData data);

    @Override
    public String getValueType(SourceData data, String property){
        BusAttr attr = propInAttrs(getBusAttrs(data),property);
        if(attr!=null)
            return attr.getValueType();
        return VALUE_TYPE_EMPTY;
    }
}
