package com.account.validator.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Sahdev
 *
 */
@SuppressWarnings("serial")
public class IndividualSourceResponse implements Serializable {

	@JsonProperty("isValid")
	private Boolean isValid;

	@JsonProperty("source")
	private String source;
	
	

	public IndividualSourceResponse() {
	}

	public IndividualSourceResponse(Boolean isValid, String source) {
		this.isValid = isValid;
		this.source = source;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean pIsValid) {
		isValid = pIsValid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String pSource) {
		source = pSource;
	}

	@Override
	public String toString() {
		return "IndividualSourceResponse [source=" + source + ", isValid=" + isValid + "]";
	}

}
