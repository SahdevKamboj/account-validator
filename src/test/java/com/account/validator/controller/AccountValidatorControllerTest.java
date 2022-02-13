package com.account.validator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.account.validator.AccountValidatorApplicationTests;
import com.account.validator.config.YAMLConfig;
import com.account.validator.service.AccountValidationProviderService;
import com.account.validator.service.AccountValidatorService;
import com.account.validator.utils.AccountValidatorTestUtils;

import reactor.core.publisher.Flux;

/**
 * 
 * @author Sahdev
 *
 */
public class AccountValidatorControllerTest extends AccountValidatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountValidatorService accountValidatorService;

	@MockBean
	private YAMLConfig yamlConfig;

	@MockBean
	private AccountValidationProviderService actProviderService;

	/**
	 * Test method with failure response 404
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateAccountWithFailur404() throws Exception {
	int statusCode =	this.mockMvc.perform(post("/validator")).andDo(print())
		.andExpect(status().is4xxClientError()).andReturn().getResponse().getStatus();//;
	assertEquals(404, statusCode);
	}

	/**
	 * Test method with success response 200 OK
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateAccountWithStatus200Ok() throws Exception {

		when(actProviderService.getApplicableProviders(Mockito.any(), Mockito.any())).thenReturn(AccountValidatorTestUtils.getResourcesMap());
		when(accountValidatorService.validateAccount(Mockito.anyList())).thenReturn(Flux.empty()); //
		int statusCode = this.mockMvc.perform(post("/v1/validator").contentType(MediaType.APPLICATION_JSON)//
				.content(AccountValidatorTestUtils.getObjectToJson(AccountValidatorTestUtils.getValidationAccountRequest(true, true))))//
		.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getStatus();//
		assertEquals(200, statusCode);
		verify(actProviderService, times(1)).getApplicableProviders(Mockito.any(), Mockito.any());
		verify(accountValidatorService, times(1)).validateAccount(Mockito.anyList());
	}

	/**
	 * Test method with failure response error message Account number is mandatory
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateAccountWithAccountNumberIsMandatory() throws Exception {

		String contentAsString = this.mockMvc.perform(post("/v1/validator") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(AccountValidatorTestUtils.getObjectToJson(AccountValidatorTestUtils.getValidationAccountRequest(true, false)))) //
				.andDo(print()).andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(); //
		String expectedMessage = "Account number is mandatory";
		assertTrue(contentAsString.contains(expectedMessage));
	}

}
