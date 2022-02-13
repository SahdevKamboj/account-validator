/**
 * 
 */
package com.account.validator.request;

import java.io.Serializable;
import java.util.List;

import com.account.validator.accountnumber.AccountNumberNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sahdev
 *
 */
@SuppressWarnings("serial")
public class ValidationAccountRequest implements Serializable {

	@JsonProperty("accountNumber")
	@AccountNumberNotNull(message =  "Account number is mandatory")
	private Long accountNumber;

	@JsonProperty("sources")
	private List<String> sources;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long pAccountNumber) {
		accountNumber = pAccountNumber;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> pSources) {
		sources = pSources;
	}

	@Override
	public String toString() {
		return "ValidationAccountRequest [accountNumber=" + accountNumber + ", sources=" + sources + "]";
	}

}
