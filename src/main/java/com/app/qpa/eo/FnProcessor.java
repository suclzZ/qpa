package com.app.qpa.eo;

import com.app.qpa.util.JepUtils;
import com.app.qpa.util.ValidateUtils;

import java.util.Date;
import java.util.Map;
/**
 *
 *
 */
public class FnProcessor {

    public enum ProcessorType{
        DATE,
        NUMBER,
        STRING
    }

    public static String exec(String fn ,Map<String,String> valiables){
        return JepUtils.valueOfJep(fn,valiables);
    }

    public static String exec(String fn,Map<String,String> valiables,ProcessorType type){
        if(type==null){
            return exec(fn,valiables);
        }else{
            switch (type){
                case DATE:
                    return processDate(fn, valiables);
                case NUMBER:
                case STRING:
                    default:
            }
        }
        return "";
    }

    private static String processDate(String fn, Map<String, String> valiables) {
        if(valiables!=null){
            for(Map.Entry<String,String> entry : valiables.entrySet()){
                String value = entry.getValue();
                Date date ;
                if((date = ValidateUtils.hasDate(value))!=null){
                    long day = date.getTime()/(1000*3600*24);//天
                    valiables.put(entry.getKey(),day+"");
                }
            }
            return exec(fn,valiables);
        }
        return "";
    }
}
