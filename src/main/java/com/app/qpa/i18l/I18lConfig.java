package com.app.qpa.i18l;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 使用 可忽略此类，在application.properties中配置即可
 * MessageSource.getMessage(key, args, locale );
 * key: i18l/messages_zh_CN.properties
 * args :
 */
//@Configuration
public class I18lConfig {

	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("i18l/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
}
