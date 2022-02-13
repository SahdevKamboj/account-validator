package com.account.validator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.account.validator.request.AccountNumberRequest;
import com.account.validator.request.ValidationAccountRequest;
import com.account.validator.response.IndividualSourceRequest;
import com.account.validator.response.IndividualSourceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AccountValidatorTestUtils {

	public static ValidationAccountRequest getValidationAccountRequest(boolean pAddResource, boolean pAddAccountNumber) {

		ValidationAccountRequest accountRequest = new ValidationAccountRequest();
		if (pAddAccountNumber) {
			accountRequest.setAccountNumber(123456l);
		}
		List<String> sources = new ArrayList<>();
		if (pAddResource) {
			sources.add("source1");
			sources.add("source2");
			accountRequest.setSources(sources);
		}

		return accountRequest;
	}

	public static String getObjectToJson(Object pObject) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(pObject);
	}

	public static Map<String, String> getResourcesMap() {
		Map<String, String> sourceMap = new HashMap<>();
		sourceMap.put("source1", "https://source1.com/v1/api/account/validate");
		sourceMap.put("source2", "https://source1.com/v2/api/account/validate");
		return sourceMap;
	}

	public static List<IndividualSourceRequest> getIndividualSources() {
		List<IndividualSourceRequest> pRequestDTO = new ArrayList<>();
		pRequestDTO.add(getIndividualSource(true));
		return pRequestDTO;
	}

	public static IndividualSourceRequest getIndividualSource(boolean isRequestWithAccountNo) {
		IndividualSourceRequest request = new IndividualSourceRequest();
		if (isRequestWithAccountNo) {
			AccountNumberRequest accountNumberRequest = new AccountNumberRequest();
			accountNumberRequest.setAccountNumber(12345l);
			request.setRequest(accountNumberRequest);
		}
		request.setSource("source1");
		request.setSourceUrl("https://source1.com/v1/api/account/validate");
		return request;
	}

	public static IndividualSourceResponse getIndividualSourceResponse() {
		IndividualSourceResponse response = new IndividualSourceResponse();
		response.setSource("source1");
		response.setIsValid(true);
		return response;
	}

}
