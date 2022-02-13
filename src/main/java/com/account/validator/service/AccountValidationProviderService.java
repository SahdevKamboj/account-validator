/**
 * 
 */
package com.account.validator.service;

import java.util.Map;

import com.account.validator.request.ValidationAccountRequest;

/**
 * @author Sahdev
 *
 */
public interface AccountValidationProviderService {

	Map<String, String> getApplicableProviders(Map<String, String> configuredProviders, ValidationAccountRequest pSourceRequest);
}
