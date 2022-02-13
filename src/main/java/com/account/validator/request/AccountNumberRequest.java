/**
 * 
 */
package com.account.validator.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sahdev
 *
 */
@SuppressWarnings("serial")
public class AccountNumberRequest implements Serializable {

	@JsonProperty("accountNumber")
	private Long accountNumber;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long pAccountNumber) {
		accountNumber = pAccountNumber;
	}

	@Override
	public String toString() {
		return "AccountNumberRequest [accountNumber=" + accountNumber + "]";
	}

}
