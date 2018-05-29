package com.sms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.bean.Request;
import com.sms.jms.JmsClient;
import com.sms.service.RequestService;
import com.sms.util.AppConfig;
import com.sms.util.ApplicationContextProvider;
import com.sms.util.Utility;

@RestController
@RequestMapping("/sms/request")
public class RequestController {

	@Autowired
	JmsClient jsmClient;

	@Autowired
	private Utility utility;

	@Autowired
	private RequestService requestService;
	private Logger logger = LogManager.getLogger(RequestController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getRequest(@RequestParam String from, @RequestParam String to,
			@RequestParam String messageId, @RequestParam String deliveryMask, @RequestParam String message,
			@RequestParam String forward, @RequestParam String fdaId) {
		Request request = ApplicationContextProvider.getBean(Request.class);
		request.setTid(utility.getUniqeTid());
		request.setFromMsisdn(from);
		request.setToMsisdn(to);
		request.setMessageId(messageId);
		request.setMessage(message);
		request.setDeliveryMask(deliveryMask);
		request.setForwardMsisdn(forward);
		request.setFdaId(fdaId);
		logger.info("Received " + request);
		// jsmClient.send(request);
		requestService.service(request);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public Request receive() {
		return jsmClient.receive();
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ResponseEntity<?> start() {
		boolean activateConsumer = ApplicationContextProvider.getBean(AppConfig.class).isActivateConsumer();
		if (activateConsumer) {
			jsmClient.start();
			return new ResponseEntity<String>("Consumer Started", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Consumer didn't activated", HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public ResponseEntity<?> stop() {
		boolean activateConsumer = ApplicationContextProvider.getBean(AppConfig.class).isActivateConsumer();
		if (activateConsumer) {
			jsmClient.stop();
			return new ResponseEntity<String>("Consumer Stoped", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Consumer didn't activated", HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> postRequest(@RequestBody Request request) {
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

}
