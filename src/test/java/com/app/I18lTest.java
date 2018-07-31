package com.app;

import java.util.Locale;

import com.app.qpa.Application;
import com.app.qpa.i18l.I18lHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 自定义MessageSource 必须为ResourceBundleMessageSource，且配置依赖同样用这个@1,参考{@link AbstractApplicationContext}
 * 若是springboot自动配置，则可以用MessageSource @2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class I18lTest {

	//@1
	@Autowired
    private MessageSource messageSource;
	//@2
//	@Autowired
//	private ResourceBundleMessageSource messageSource;


//	@Test
	public void t1() {
		Locale locale = LocaleContextHolder.getLocale();
		String info = messageSource.getMessage("info", new String []{"tom"}, locale );
	//	String info = messageSource.getMessage("message", null , null);
		System.out.println(">>>>>>>>>>>>>>>>>  "+info);
	}

	@Test
	public void t2(){
		MessageSource ms = I18lHelper.getInstance().getMessageSource();
		String info = I18lHelper.getMessage("info", new String[]{"tom"}, null);
		System.err.println( "info : " +info);
	}
}
