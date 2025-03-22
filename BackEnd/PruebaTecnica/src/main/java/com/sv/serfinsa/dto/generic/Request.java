package com.sv.serfinsa.dto.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sv.serfinsa.util.Constant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Request<T> {

	@JsonProperty("metadata")
	@NotNull(message = Constant.MSG_NULL)
	@Valid
	private Metadata metadata;
	
	@JsonProperty("data")
	@NotNull(message = Constant.MSG_NULL)
	@Valid
	private DataRequest<T> data;

	public Request() {
	}
	
	

	public Request(@NotNull(message = "no contiene valor") Metadata metadata,
			@NotNull(message = "no contiene valor") DataRequest<T> data) {
		super();
		this.metadata = metadata;
		this.data = data;
	}



	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public DataRequest<T> getData() {
		return data;
	}

	public void setData(DataRequest<T> data) {
		this.data = data;
	}

	
	
}
