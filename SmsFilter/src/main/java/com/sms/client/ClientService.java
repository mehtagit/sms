package com.sms.client;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Component;

import com.sms.bean.Request;
import com.sms.util.Profile;

@Component
public class ClientService {
	@PersistenceContext
	private EntityManager entityManager;

	private StoredProcedureQuery query;

	@PostConstruct
	public void registerStroredProcedure() {
		query = entityManager.createStoredProcedureQuery("getcallScreening");

		// Declare the parameters in the same order
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);

	}

	public Boolean checkProfile(Request request) {

		// "login" this is the name of your procedure

		// Pass the parameter values
		query.setParameter(1, "");
		query.setParameter(2, "");
		query.setParameter(3, new Date());
		query.setParameter(4, 3);
		// Execute query
		query.execute();

		// Get output parameters
		Integer outCode = (Integer) query.getOutputParameterValue(5);
		String outMessage = (String) query.getOutputParameterValue(6);
		request.setProfile(Profile.Graylist);
		return true; // enter your condition
	}
}
