/**
 * 
 */
package com.account.validator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.account.validator.config.YAMLConfig;
import com.account.validator.request.AccountNumberRequest;
import com.account.validator.request.ValidationAccountRequest;
import com.account.validator.response.AccountValidationResponse;
import com.account.validator.response.IndividualSourceRequest;
import com.account.validator.service.AccountValidationProviderService;
import com.account.validator.service.AccountValidatorService;

/**
 * @author Sahdev
 *
 */
@RestController
@RequestMapping("v{version:[1]}/validator")
public class AccountValidatorController {

	Logger logger = LoggerFactory.getLogger(AccountValidatorController.class);

	@Autowired
	private AccountValidatorService accountValidatorService;

	@Autowired
	private YAMLConfig yamlConfig;

	@Autowired
	private AccountValidationProviderService actProviderService;

	/**
	 * Validate account on the basis of account number
	 * 
	 * @param pVersion
	 * @param validationAccountRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountValidationResponse validateAccount(@PathVariable("version") int pVersion, @RequestBody @Valid ValidationAccountRequest validationAccountRequest) {

		logger.debug("validateAccount({},{})", pVersion, validationAccountRequest);

		long startTime = System.currentTimeMillis();

		Map<String, String> sourcesMap = actProviderService.getApplicableProviders(yamlConfig.getProviders(), validationAccountRequest);
		AccountValidationResponse accountValidationResponse = new AccountValidationResponse();

		List<IndividualSourceRequest> individualSourceRequestLis = new ArrayList<>();

		sourcesMap.entrySet().stream().map(p -> setRequestData(individualSourceRequestLis, validationAccountRequest, p.getKey(), p.getValue())).collect(Collectors.toList());

		accountValidationResponse.setResult(accountValidatorService.validateAccount(individualSourceRequestLis).collectList().blockOptional().orElse(Collections.emptyList()));

		long endTime = System.currentTimeMillis();
		long totalTimeTaken = (endTime - startTime) / 1000;
		logger.debug("Total time taking for responce in second : {} ", totalTimeTaken);

		return accountValidationResponse;
	}

	/**
	 * Map request data
	 * 
	 * @param pIndividualSourceRequestLis
	 * @param pValidationAccountRequest
	 * @param pKey
	 * @param pValue
	 * @return
	 */
	private Object setRequestData(List<IndividualSourceRequest> pIndividualSourceRequestLis, ValidationAccountRequest pValidationAccountRequest, String pKey, String pValue) {

		IndividualSourceRequest individualSourceRequest = new IndividualSourceRequest();
		AccountNumberRequest accountNumberRequest = new AccountNumberRequest();
		accountNumberRequest.setAccountNumber(pValidationAccountRequest.getAccountNumber());
		individualSourceRequest.setRequest(accountNumberRequest);
		individualSourceRequest.setSourceUrl(pValue);
		individualSourceRequest.setSource(pKey);
		pIndividualSourceRequestLis.add(individualSourceRequest);
		return pIndividualSourceRequestLis;
	}

}
