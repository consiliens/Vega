/*******************************************************************************
 * Copyright (c) 2011 Subgraph.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Subgraph - initial API and implementation
 ******************************************************************************/
package com.subgraph.vega.ui.http.intercept;

import java.net.URISyntaxException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import com.subgraph.vega.api.http.requests.IHttpRequestBuilder;
import com.subgraph.vega.api.http.requests.IHttpRequestEngine;
import com.subgraph.vega.api.http.requests.IHttpResponseBuilder;

/**
 * Provides a modifiable snapshot of the transaction held by the TransactionManager.
 */
public class TransactionInfo {
	private int requestTransactionSerial;
	private IHttpRequestBuilder requestBuilder;
	private boolean requestHasContent;
	private TransactionManager.TransactionStatus requestStatus;
	private String requestStatusMessage;
	private int requestSerial;
	private int responseTransactionSerial;
	private IHttpResponseBuilder responseBuilder;
	private boolean responseHasContent;
	private TransactionManager.TransactionStatus responseStatus;
	private String responseStatusMessage;
	private int responseSerial;
	
	public TransactionInfo(IHttpRequestEngine requestEngine) {
		requestTransactionSerial = -1;
		requestBuilder = requestEngine.createRequestBuilder();
		requestHasContent = false;
		setRequestStatus(TransactionManager.TransactionStatus.STATUS_INACTIVE);
		requestSerial = -1;
		responseBuilder = requestEngine.createResponseBuilder();
		responseHasContent = false;
		setResponseStatus(TransactionManager.TransactionStatus.STATUS_INACTIVE);
		responseSerial = -1;
	}

	public void setRequestTransactionSerial(int requestTransactionSerial) {
		this.requestTransactionSerial = requestTransactionSerial;
	}
	
	public int getRequestTransactionSerial() {
		return requestTransactionSerial;
	}

	public void setFromRequest(HttpRequest request) throws URISyntaxException {
		requestBuilder.setFromRequest(request);
	}

	public IHttpRequestBuilder getRequestBuilder() {
		return requestBuilder;
	}

	public void setRequestHasContent(boolean requestHasContent) {
		this.requestHasContent = requestHasContent;
	}
	
	public boolean requestHasContent() {
		return requestHasContent;
	}

	public void setRequestStatus(TransactionManager.TransactionStatus requestStatus) {
		this.requestStatus = requestStatus;
		switch (requestStatus) {
		case STATUS_INACTIVE:
			requestStatusMessage = "No request pending";
			break;
		case STATUS_PENDING:
			requestStatusMessage = "Request pending to " + getRequestHostPart();
			break;
		case STATUS_SENT:
			requestStatusMessage = "Request sent, awaiting response";
			break;
		}
	}
	
	private String getRequestHostPart() {
		final StringBuilder buf = new StringBuilder();
		buf.append(requestBuilder.getScheme());
		buf.append("://");
		buf.append(requestBuilder.getHost());
		if (requestBuilder.getHostPort() != -1) {
			buf.append(':');
			buf.append(Integer.toString(requestBuilder.getHostPort()));
		}
		return buf.toString();
	}

	public TransactionManager.TransactionStatus getRequestStatus() {
		return requestStatus; 
	}

	public boolean requestIsPending() {
		return (requestStatus == TransactionManager.TransactionStatus.STATUS_PENDING);
	}

	public String getRequestStatusMessage() {
		return requestStatusMessage;
	}
	
	public void setRequestSerial(int serial) {
		this.requestSerial = serial;
	}

	public int getRequestSerial() {
		return requestSerial;
	}

	public void setResponseTransactionSerial(int responseTransactionSerial) {
		this.responseTransactionSerial = responseTransactionSerial;
	}
	
	public int getResponseTransactionSerial() {
		return responseTransactionSerial;
	}

	public void setFromResponse(HttpResponse response) throws URISyntaxException {
		responseBuilder.setFromResponse(response);
	}

	public IHttpResponseBuilder getResponseBuilder() {
		return responseBuilder;
	}

	public void setResponseHasContent(boolean responseHasContent) {
		this.responseHasContent = responseHasContent;
	}
	
	public boolean responseHasContent() {
		return responseHasContent;
	}

	public void setResponseStatus(TransactionManager.TransactionStatus responseStatus) {
		this.responseStatus = responseStatus;
		switch (responseStatus) {
		case STATUS_INACTIVE:
			responseStatusMessage = "No response pending";
			break;
		case STATUS_PENDING:
			responseStatusMessage = "Response pending";
			break;
		}
	}

	public TransactionManager.TransactionStatus getResponseStatus() {
		return responseStatus; 
	}

	public boolean responseIsPending() {
		return (responseStatus == TransactionManager.TransactionStatus.STATUS_PENDING);
	}

	public String getResponseStatusMessage() {
		return responseStatusMessage;
	}
	
	public void setResponseSerial(int serial) {
		this.responseSerial = serial;
	}

	public int getResponseSerial() {
		return responseSerial;
	}
	
}
