package com.sms.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.bean.Request;
import com.sms.client.ClientService;
import com.sms.controller.RequestController;
import com.sms.util.Profile;
import com.sms.util.Utility;

@Service
public class RequestService {

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	ClientService clientService;

	@Autowired
	@Resource(name = "urls")
	Urls urls;

	@Autowired
	Utility utility;

	private final Logger logger = LoggerFactory.getLogger(RequestController.class);

	private void setProfile(Request request) {
		request.setProfile(Profile.WhiteList);

		logger.info("Found Profile" + request);
	}

	public void save(Request request) {
		requestRepository.save(request);
		logger.info("Saved in DB " + request);
	}

	public void delete(Request request) {
		requestRepository.delete(request);
		logger.info("Deleted from DB " + request);
	}

	public void action(Request request) {
		save(request);

		clientService.checkProfile(request);

		String url = null;
		switch (request.getProfile()) {
		case Blacklist:
			url = null;
			delete(request);
			break;
		case WhiteList:
			url = urls.getUrl();
			url = url.replaceAll("<from>", request.getFromMsisdn());
			url = url.replaceAll("<to>", request.getToMsisdn());
			url = url.replaceAll("<text>", request.getMessage());
			break;
		case Graylist:
			url = urls.getUrl();
			url = url.replaceAll("<from>", request.getFromMsisdn());
			url = url.replaceAll("<to>", request.getForwardMsisdn());
			url = url.replaceAll("<text>", request.getMessage());
			break;
		}

		if (url != null) {
			logger.info("URL : " + url + request);
			// utility.callUrl(url);
			delete(request);
		}
	}

}
