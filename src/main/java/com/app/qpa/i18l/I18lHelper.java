package com.app.qpa.i18l;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 *  不需要引用MessageSource,直接通过下面的方法调用
 *  I18lHelper.getMessage("msg",null,null);
 */
@Component
public class I18lHelper{
    private static I18lHelper i18lHelper;

    @Autowired
    private MessageSource messageSource;

    public static I18lHelper getInstance(){
        return i18lHelper;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @PostConstruct
    public void initSelf(){
        i18lHelper = this;
    //    i18lHelper.messageSource = this.messageSource;
    }

    public static String getMessage(String code, String[] args, Locale locale){
     //   locale =  Locale.CHINA;
        return  i18lHelper.messageSource.getMessage(code,args,locale);
    }

}
