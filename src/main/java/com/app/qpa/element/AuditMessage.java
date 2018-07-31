package com.app.qpa.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 稽核结果信息
 *
 */

public class AuditMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1316408137379391596L;
	
	private String node;//记录异常信息
	private List<AuditMessage> auditMessages;//记录子集
	
	public void addAtMessages(AuditMessage msg) {
		if(auditMessages==null) {
			this.auditMessages = new ArrayList<AuditMessage>();
			auditMessages.add(msg);
		}else {
			auditMessages.add(msg);
		}
	}
	
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<AuditMessage> getAtMessages() {
		return auditMessages;
	}

	public void setAtMessages(List<AuditMessage> auditMessages) {
		this.auditMessages = auditMessages;
	}
	
	@Override
	public String toString() {
		return "node:"+node + ">>children:"+auditMessages;
	}
}