package com.sv.serfinsa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sv.serfinsa.util.Constant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DataRequest<T> {

	@JsonProperty("body")
	@NotNull(message = Constant.MSG_NULL)
	@Valid
	private T body;

	public DataRequest() {
	}

	public DataRequest(T body) {
		super();
		this.body = body;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
}
