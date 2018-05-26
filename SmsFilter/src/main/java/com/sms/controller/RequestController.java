package com.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sms.util.Utility;

@RestController
@RequestMapping("/sms/request")
public class RequestController {

	@Autowired
	JmsClient jsmClient;

	@Autowired
	private Utility utility;

	private final Logger logger = LoggerFactory.getLogger(RequestController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getRequest(@RequestParam String from, @RequestParam String to,
			@RequestParam String messageId, @RequestParam String deliveryMask, @RequestParam String message,
			@RequestParam String forward, @RequestParam String fdaId) {
		Request request = new Request();
		request.setTid(utility.getUniqeTid());
		request.setFromMsisdn(from);
		request.setToMsisdn(to);
		request.setMessageId(messageId);
		request.setMessage(message);
		request.setDeliveryMask(deliveryMask);
		request.setForwardMsisdn(forward);
		request.setFdaId(fdaId);
		jsmClient.send(request);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public Request receive() {
		return jsmClient.receive();
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ResponseEntity<?> start() {
		jsmClient.start();
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public ResponseEntity<?> stop() {
		jsmClient.stop();
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> postRequest(@RequestBody Request request) {
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

}
