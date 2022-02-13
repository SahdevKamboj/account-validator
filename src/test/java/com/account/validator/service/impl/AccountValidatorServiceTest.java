/**
 * 
 */
package com.account.validator.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.account.validator.response.IndividualSourceResponse;
import com.account.validator.service.WebClientService;
import com.account.validator.utils.AccountValidatorTestUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sahdev
 *
 */
@ExtendWith(SpringExtension.class)
public class AccountValidatorServiceTest {

	@InjectMocks
	private AccountValidatorServiceImpl validatorServiceImpl;

	@Mock
	private WebClientService webClientService;

	/**
	 * Test method with first response null
	 */
	@Test
	public void testValidateAccountWithFirstResponseNull() {
		when(webClientService.getInstance(Mockito.anyString())).thenReturn(WebClient.create());
		Flux<IndividualSourceResponse> flux = validatorServiceImpl.validateAccount(AccountValidatorTestUtils.getIndividualSources());
		IndividualSourceResponse individualSourceResponse= flux.blockFirst();
		assertNull(individualSourceResponse);
	}
	
	/**
	 * Test method with response not null
	 */
	@Test
	public void testGetValidateAccountWithSuccess() {

		when(webClientService.getInstance(Mockito.anyString())).thenReturn(WebClient.create());
		Mono<IndividualSourceResponse> mono = validatorServiceImpl.getValidateAccount(AccountValidatorTestUtils.getIndividualSource(true));
		verify(webClientService, times(1)).getInstance(Mockito.anyString());
		assertNotNull(mono);
	}

	/**
	 * Test method with throw  IllegalArgumentException error message is "Account number not found !"
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetValidateAccountWithAccountNumberNull() throws IllegalArgumentException {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			validatorServiceImpl.getValidateAccount(AccountValidatorTestUtils.getIndividualSource(false));
		});

		String expectedMessage = "Account number not found !";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
