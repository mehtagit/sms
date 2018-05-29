package com.sms.client;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.sms.bean.Request;
import com.sms.util.Profile;

public class ClientService {

	private SimpleJdbcCall jdbcCall;

	public ClientService(BasicDataSource clientDataSource) {
		this.jdbcCall = new SimpleJdbcCall(clientDataSource).withProcedureName("getcallScreening");
		this.jdbcCall.declareParameters(new SqlParameter(ClientParametersConstants.P_PHONE_NUMBER, Types.VARCHAR));
		this.jdbcCall.declareParameters(new SqlParameter(ClientParametersConstants.P_NUMA, Types.VARCHAR));
		this.jdbcCall.declareParameters(new SqlParameter(ClientParametersConstants.P_CALLDATE, Types.TIMESTAMP));
		this.jdbcCall.declareParameters(new SqlParameter(ClientParametersConstants.P_SERVICE_TYPE, Types.INTEGER));
		this.jdbcCall.declareParameters(new SqlOutParameter(ClientParametersConstants.P_ACTION, Types.INTEGER));
		this.jdbcCall.declareParameters(new SqlOutParameter(ClientParametersConstants.P_FW_NUM, Types.VARCHAR));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean checkProfile(Request request) {

		// "login" this is the name of your procedure

		// Pass the parameter values
		Map inParameters = new HashMap();
		inParameters.put(ClientParametersConstants.P_PHONE_NUMBER, "asasa");
		inParameters.put(ClientParametersConstants.P_NUMA, "89");
		inParameters.put(ClientParametersConstants.P_CALLDATE, new Date());
		inParameters.put(ClientParametersConstants.P_SERVICE_TYPE, 1);
		Map<String, Object> outParameters = this.jdbcCall.execute(inParameters);

		// Get output parameters
		Integer outCode = (Integer) outParameters.get(ClientParametersConstants.P_ACTION);
		String outMessage = (String) outParameters.get(ClientParametersConstants.P_FW_NUM);

		request.setProfile(Profile.Graylist);
		return true; // enter your condition
	}
}
