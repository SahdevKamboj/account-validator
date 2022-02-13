/**
 * 
 */
package com.account.validator.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.account.validator.utils.AccountValidatorTestUtils;

/**
 * @author Sahdev
 *
 */
@ExtendWith(SpringExtension.class)
public class AccountValidationProviderServiceImplTest {

	@InjectMocks
	private AccountValidationProviderServiceImpl providerServiceImpl;
	
	@Test
	public void testGetApplicableProvidersIfSourceListNotNull() {
		Map<String, String> map = providerServiceImpl.getApplicableProviders(AccountValidatorTestUtils.getResourcesMap(), AccountValidatorTestUtils.getValidationAccountRequest(true, true));
		assertNotNull(map);
		
	}
	
	
	@Test
	public void testGetApplicableProvidersIfSourceIsNull() {
		Map<String, String> map = providerServiceImpl.getApplicableProviders(AccountValidatorTestUtils.getResourcesMap(),  AccountValidatorTestUtils.getValidationAccountRequest(false, true));
		assertNotNull(map);
		
	}
	
	
}
