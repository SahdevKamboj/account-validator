/**
 * 
 */
package com.account.validator.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.account.validator.request.ValidationAccountRequest;
import com.account.validator.service.AccountValidationProviderService;

/**
 * @author Sahdev
 *
 */
@Service
public class AccountValidationProviderServiceImpl implements AccountValidationProviderService {

	private static final Logger logger = LoggerFactory.getLogger(AccountValidationProviderServiceImpl.class);

	/**
	 * filter out applicable service provider
	 */
	@Override
	public Map<String, String> getApplicableProviders(Map<String, String> configuredProviders, ValidationAccountRequest validationAccountRequest) {

		logger.debug("getApplicableProviders({}, {} )", configuredProviders, validationAccountRequest);

		if (null != validationAccountRequest.getSources() && !validationAccountRequest.getSources().isEmpty()) {
			return validationAccountRequest.getSources().stream().filter(source -> configuredProviders != null && configuredProviders.containsKey(source)).collect(Collectors.toMap(source -> source, source -> configuredProviders.get(source)));

		} else {
			return Collections.unmodifiableMap(configuredProviders);
		}

	}

}
