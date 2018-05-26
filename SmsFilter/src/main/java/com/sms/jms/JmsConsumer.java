package com.sms.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sms.bean.Request;
import com.sms.service.RequestService;

@Component
public class JmsConsumer implements Runnable {
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	RequestService requestService;
	
	@Value("${jms.queue.destination}")
	String destinationQueue;
	private final Logger logger = LoggerFactory.getLogger(JmsClient.class);

	private boolean isRunning;

	public Request receive() {
		return (Request) jmsTemplate.receiveAndConvert(destinationQueue);
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			Request request = (Request) jmsTemplate.receiveAndConvert(destinationQueue);
			logger.info(request.toString());
			requestService.action(request);
		}
		logger.info(Thread.currentThread().getName() + " Thread Stopped");
	}

	public void start() {
		new Thread(this, "JmsClient").start();
	}

	public void stop() {
		this.isRunning = false;
	}
}