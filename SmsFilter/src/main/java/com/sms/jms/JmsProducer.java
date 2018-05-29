package com.sms.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sms.bean.Request;
import com.sms.util.AppConfig;

@Component
public class JmsProducer {
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private AppConfig appConfig;

	public void send(Request request) {
		jmsTemplate.convertAndSend(appConfig.getJmsQueue(), request);
	}
}