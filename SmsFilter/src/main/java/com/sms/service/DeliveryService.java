package com.sms.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.bean.Delivery;
import com.sms.repository.RequestRepository;
import com.sms.util.Utility;

@Service
public class DeliveryService {

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	Utility utility;

	private final Logger logger = LogManager.getLogger(RequestService.class);

	public void delete(Delivery delivery) {
		requestRepository.delete(delivery.getTid());
		logger.info("Deleted from DB " + delivery);
	}

}
