package com.account.validator.service;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * 
 * @author Sahdev
 *
 */
public interface WebClientService {

	public WebClient getInstance(String clientURL);

}
