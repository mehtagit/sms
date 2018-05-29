package com.sms.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sms.bean.Request;
import com.sms.controller.DeliveryController;
import com.sms.service.RequestService;
import com.sms.util.AppConfig;

@Component
public class JmsConsumer implements Runnable {
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	RequestService requestService;

	@Autowired
	private AppConfig appConfig;

	private Logger logger = LogManager.getLogger(JmsConsumer.class);

	private boolean isRunning;

	public Request receive() {
		return (Request) jmsTemplate.receiveAndConvert(appConfig.getJmsQueue());
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			Request request = receive();
			logger.info(request.toString());
			requestService.service(request);
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