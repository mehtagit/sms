package com.sms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.bean.Delivery;
import com.sms.controller.RequestController;
import com.sms.repository.RequestRepository;
import com.sms.util.Utility;

@Service
public class DeliveryService {

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	Utility utility;

	private final Logger logger = LoggerFactory.getLogger(RequestController.class);

	public void delete(Delivery delivery) {
		requestRepository.delete(delivery.getTid());
		logger.info("Deleted from DB " + delivery);
	}

}
