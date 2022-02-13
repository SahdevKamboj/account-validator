/**
 * 
 */
package com.account.validator.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sahdev
 *
 */
@SuppressWarnings("serial")
public class AccountValidationResponse implements Serializable {

	@JsonProperty("result")
	private List<IndividualSourceResponse> result;

	public List<IndividualSourceResponse> getResult() {
		return result;
	}

	public void setResult(List<IndividualSourceResponse> pResult) {
		result = pResult;
	}

	@Override
	public String toString() {
		return "AccountValidationResponse [result=" + result + "]";
	}

}
