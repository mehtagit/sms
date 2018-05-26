package com.sms.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sms.bean.Request;

@Component
public class JmsProducer {
	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${jms.queue.destination}")
	String destinationQueue;

	public void send(Request request) {
		jmsTemplate.convertAndSend(destinationQueue, request);
	}
}