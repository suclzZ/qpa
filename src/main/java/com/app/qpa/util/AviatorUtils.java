package com.app.qpa.util;

import com.app.qpa.exception.QpaException;
import com.app.qpa.service.NumberAble;
import com.googlecode.aviator.AviatorEvaluator;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Aviator计算工具
 *
 */
public class AviatorUtils {

    private static Logger logger = LoggerFactory.getLogger(AviatorUtils.class);

    /**
     * + - * /
     * @param fn
     * @param evn Map的value类型决定返回值类型
     * @return
     */
    public static Double execute(String fn,Map<String,Object> evn){
        List<String> variables = ValueUtils.findVarFromFn(fn);//所有变量

        if(evn!=null){
            Set<String> evnKeySet = evn.keySet();
            if(!evnKeySet.containsAll(variables)){
                throw new QpaException("evn没有包含所有变量");
            }
            for(Map.Entry<String,Object> entry : evn.entrySet()){
                Object evnVal = entry.getValue();
                if(!variables.contains(entry.getKey())) continue;
                if(evnVal==null) evnVal = 0;
                if(evnVal instanceof String){
                    if(NumberUtils.isNumber(((String) evnVal).trim())){
                        evnVal = Double.valueOf(((String) evnVal).trim());
                        evn.put(entry.getKey(),evnVal);
                    }else{
                        logger.error(" {} is not a number",evnVal);
                    }
                }else if(evnVal instanceof Long || evnVal instanceof Integer){
                    evnVal = Double.valueOf(evnVal.toString());
                }else if(evnVal instanceof Double){

                }else if(evnVal instanceof NumberAble){
                    evnVal = ((NumberAble) evnVal).toNumber();
                }
                else{
                    //don't support type
                    logger.error("don't support type {},and set default value 0.0",evnVal.getClass());
                    evnVal = 0.0;
                }
            }
        }

        Object value = AviatorEvaluator.execute(fn, evn);

        return new Double(value.toString());
    }

    /**
     * 没对数据处理 evn必须都为数字 表达式必须只能包含 > >= < <=
     * @param expression
     * @param evn
     * @return
     */
    public static boolean compare(String expression , Map<String,Object> evn){
        // ValueUtils.findExpVarFromFn(expression);
        Object result = AviatorEvaluator.execute(expression, evn);
        if(result instanceof  Boolean){
            return (Boolean) result;
        }
        logger.warn("expression don't support compare");
        return false;
    }
}
