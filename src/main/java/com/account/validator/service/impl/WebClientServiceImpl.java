package com.account.validator.service.impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.account.validator.service.WebClientService;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
/**
 * @author Sahdev
 *
 */
@Service
public class WebClientServiceImpl implements WebClientService {

	private static final Logger logger = LoggerFactory.getLogger(WebClientServiceImpl.class);

	/**
	 * Get web client instance and cache if same URL request again 
	 */
	@Override
	@Cacheable("webclients")
	public WebClient getInstance(String clientURL) {
		logger.debug("Return a new WebClient for : {}", clientURL);
		return WebClient.builder().baseUrl(clientURL)
				.clientConnector(
						new ReactorClientHttpConnector(HttpClient.create().responseTimeout(Duration.ofSeconds(1))))
				.filter(logRequest()).filter(logResponse()).build();
	}

	/**
	 * Request logs method for inject log in all request
	 * 
	 * @return
	 */
	private ExchangeFilterFunction logRequest() {

		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			logger.debug("Request : {} , {}", clientRequest.method(), clientRequest.url());
			return Mono.just(clientRequest);
		});

	}

	/**
	 * Response logs method for inject log in all response
	 * 
	 * @return
	 */
	private ExchangeFilterFunction logResponse() {

		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			logger.debug("Response status code : {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});

	}

}
