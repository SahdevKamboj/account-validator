/**
 * 
 */
package com.account.validator.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Sahdev
 *
 */
@ExtendWith(SpringExtension.class)
public class WebClientServiceTest {

	@InjectMocks
	private WebClientServiceImpl clientService;

	@Test
	public void testGetInstance() {
		WebClient webClient = clientService.getInstance("https://source1.com/v1/api/account/validate");
		assertNotNull(webClient);

	}

}
