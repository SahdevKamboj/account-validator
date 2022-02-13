/**
 * 
 */
package com.account.validator.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.account.validator.response.IndividualSourceRequest;
import com.account.validator.response.IndividualSourceResponse;
import com.account.validator.service.AccountValidatorService;
import com.account.validator.service.WebClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sahdev
 *
 */
@Service
public class AccountValidatorServiceImpl implements AccountValidatorService {

	public static final Logger logger = LoggerFactory.getLogger(AccountValidatorServiceImpl.class);

	@Autowired
	private WebClientService webClientService;

	/**
	 * Making ASync call for all services
	 * 
	 * @param pRequestDTO
	 * @return
	 */
	@Override
	public Flux<IndividualSourceResponse> validateAccount(List<IndividualSourceRequest> pRequestDTO) {
		return Flux.fromIterable(pRequestDTO).flatMap(this::getValidateAccount);
	}

	/**
	 * Check validation on the basis of account id with below single parameter
	 * 
	 * @param pRequest
	 * @return
	 */
	protected Mono<IndividualSourceResponse> getValidateAccount(IndividualSourceRequest pRequest) throws IllegalArgumentException {
		logger.debug("getSourceValidate({})", pRequest);

		if (null == pRequest.getRequest() || null == pRequest.getRequest().getAccountNumber()) {

			throw new IllegalArgumentException("Account number not found !");
		}

		Mono<IndividualSourceResponse> responseDTO = webClientService.getInstance(pRequest.getSourceUrl())
			.post().bodyValue(pRequest.getRequest())
			.accept(MediaType.APPLICATION_JSON).retrieve()
			.bodyToMono(IndividualSourceResponse.class)
			.onErrorResume(e -> {
				logger.error(" {} service is not working due to [{}] issue, service url is : {} ",pRequest.getSource(), e.getMessage(), pRequest.getSourceUrl());
				return Mono.empty();
				});

		return responseDTO.doOnSuccess(p -> setSource(p, pRequest.getSource()));

	}

	/**
	 * set source value into response
	 * 
	 * @param pResponseDTO
	 * @param source
	 */
	private void setSource(IndividualSourceResponse pResponseDTO, String source) {

		if (null != pResponseDTO) {
			pResponseDTO.setSource(source);
		}
	}

}
