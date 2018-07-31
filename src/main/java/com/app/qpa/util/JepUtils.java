package com.app.qpa.util;

import com.app.qpa.exception.QpaException;
import org.apache.commons.lang3.math.NumberUtils;
import org.nfunk.jep.JEP;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * jep计算工具
 *
 */
public class JepUtils {
    /**
     * 通过jep对表达式进行运算
     * @param fn 表达式，包含变量
     * @param bean 表达式变量对应的对象
     * @return 数组字符串
     */
    public static String valueOfJep(String fn,Object bean) {
        List<String> variables = ValueUtils.findVarFromFn(fn);
        JEP jep = buildJep(variables,bean);
        jep.parseExpression(fn);
        if(jep.hasError()) {
            String error = jep.getErrorInfo();//存在错误
            throw new QpaException(ValueUtils.convert("jep执行错误：{}",error));
        }else {
            return jep.getValueAsObject().toString();
        }
    }

    /**
     * 构建jep表达式，并添加变量与其值
     * @param variables
     * @param data
     * @return
     */
    public static JEP buildJep(List<String> variables, Object data) {
        JEP jep = new JEP();
        if(variables!=null) {
            for(String var :variables) {
                if(!StringUtils.isEmpty(var) && !NumberUtils.isNumber(var)) {
                    jep.addVariableAsObject(var, StringUtils.isEmpty(ValueUtils.valueFromBean(data,var))?0:Double.valueOf(ValueUtils.valueFromBean(data,var)));
                }
            }
        }
        return jep;
    }
}
