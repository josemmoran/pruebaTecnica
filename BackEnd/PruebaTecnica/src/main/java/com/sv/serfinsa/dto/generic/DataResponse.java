package com.sv.serfinsa.dto.generic;

public class DataResponse {
	
	private Object data;

	public DataResponse(Object data) {
		this.data = data;
	}

	public DataResponse() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
