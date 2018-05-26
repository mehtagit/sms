package com.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sms.bean.Delivery;
import com.sms.bean.Request;

@RestController
@RequestMapping("/sms/delivery")
public class DeliveryController {

	private final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> getRequest(@RequestBody Delivery delivery) {
		logger.info("Creating User : {}", delivery);

		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.
		 * getId()).toUri()); return new ResponseEntity<String>(headers,
		 * HttpStatus.CREATED);
		 */
		return new ResponseEntity<Delivery>(delivery, HttpStatus.CREATED);
	}

}
