/**
 * 
 */
package com.account.validator.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sahdev
 *
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

	private Map<String, String> providers;

	public Map<String, String> getProviders() {
		return providers;
	}

	public void setProviders(Map<String, String> pProviders) {
		providers = pProviders;
	}

	@Override
	public String toString() {
		return "YAMLConfig [providers=" + providers + "]";
	}

}
