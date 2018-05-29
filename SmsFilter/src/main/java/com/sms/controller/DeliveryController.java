package com.sms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.bean.Delivery;
import com.sms.service.DeliveryService;
import com.sms.util.ApplicationContextProvider;

@RestController
@RequestMapping("/sms/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	private Logger logger = LogManager.getLogger(DeliveryController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getDelivery(@RequestParam String tid, @RequestParam String messageId) {
		Delivery delivery = ApplicationContextProvider.getBean(Delivery.class);
		delivery.setTid(tid);
		delivery.setMessageId(messageId);
		logger.info("Received " + delivery);
		deliveryService.delete(delivery);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
