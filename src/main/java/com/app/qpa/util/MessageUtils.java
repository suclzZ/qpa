package com.app.qpa.util;

import com.app.qpa.element.AuditMessage;
import com.app.qpa.element.Item;
import com.app.qpa.element.Rule;
import com.app.qpa.element.Type;
import org.springframework.util.StringUtils;

import com.app.qpa.element.QpaModal;

public class MessageUtils {
	
	public static final String SEPARATOR = ":";

	public static String build(QpaModal modal) {
		StringBuilder infoBuilder = new StringBuilder();
		if(modal instanceof Type) {
			infoBuilder.append("稽核类型校验失败:");
		}else if(modal instanceof Item) {
			infoBuilder.append("稽核项校验失败:");
		}else if(modal instanceof Rule) {
			infoBuilder.append("稽核条件校验失败:");
		}
		infoBuilder.append(modal.getCode())
		.append(SEPARATOR)
		.append(modal.getName());
		return infoBuilder.toString();
		
	}
	
	public static boolean isNull(AuditMessage message) {
		if(message == null) return true;
		if(StringUtils.isEmpty(message.getNode())) return true;
		if(message.getAtMessages()==null|| message.getAtMessages().size()==0) return true;
		return false;
	}

}
