package com.sms.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Delivery {
	private String messageId;
	private String tid;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "Delivery [" + (messageId != null ? "messageId=" + messageId + ", " : "")
				+ (tid != null ? "tid=" + tid : "") + "]";
	}

}
