package com.sms.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.bean.Request;

@Service
public class JmsClientImpl implements JmsClient {

	@Autowired
	JmsConsumer jmsConsumer;

	@Autowired
	JmsProducer jmsProducer;

	public void send(Request request) {
		jmsProducer.send(request);
	}

	public Request receive() {
		return jmsConsumer.receive();
	}

	public void start() {
		jmsConsumer.start();
	}

	public void stop() {
		jmsConsumer.stop();
	}

}