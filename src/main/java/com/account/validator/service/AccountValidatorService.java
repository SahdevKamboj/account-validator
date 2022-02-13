/**
 * 
 */
package com.account.validator.service;

import java.util.List;

import com.account.validator.response.IndividualSourceRequest;
import com.account.validator.response.IndividualSourceResponse;

import reactor.core.publisher.Flux;

/**
 * @author Sahdev
 *
 */
public interface AccountValidatorService {

	Flux<IndividualSourceResponse> validateAccount(List<IndividualSourceRequest> pRequestDTO);
	

}
