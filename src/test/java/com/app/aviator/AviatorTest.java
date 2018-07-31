package com.app.aviator;

import com.app.qpa.util.AviatorUtils;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
public class AviatorTest {

    public static void main(String[] args) {

        Object val = AviatorEvaluator.exec("1+2");

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("a",6.0);
        map.put("b",10);
        map.put("c",2);
        map.put("d",4);
        map.put("e","3");
        map.put("f",new Object());
        map.put("g","");

        String fn = "a + b -c*d";
        Object val1 = AviatorEvaluator.execute(fn, map);

        System.out.println( "val1 :"+val1);

        String fn1 = "a + b -c*e";
        Double val2 = AviatorUtils.execute(fn1, map);

        System.out.println( "val2 :"+val2);


        Object val3 = AviatorEvaluator.execute("a<b",map);

        System.out.println("val3: " +val3);

    }
}
