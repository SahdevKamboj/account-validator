package com.account.validator.response;

import com.account.validator.request.AccountNumberRequest;


/**
 * 
 * @author Sahdev
 *
 */
public class IndividualSourceRequest {

	private AccountNumberRequest request;

	private String sourceUrl;

	private String source;

	public AccountNumberRequest getRequest() {
		return request;
	}

	public void setRequest(AccountNumberRequest pRequest) {
		request = pRequest;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String pSourceUrl) {
		sourceUrl = pSourceUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String pSource) {
		source = pSource;
	}

	@Override
	public String toString() {
		return "IndividualSourceRequest [request=" + request + ", sourceUrl=" + sourceUrl + ", source=" + source + "]";
	}

}
