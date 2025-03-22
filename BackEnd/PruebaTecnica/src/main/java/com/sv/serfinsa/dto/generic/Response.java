package com.sv.serfinsa.dto.generic;

public class Response {

	private Metadata metadata;
	
	private DataResponse data;

	public Response(Metadata metadata, DataResponse data) {
		super();
		this.metadata = metadata;
		this.data = data;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public DataResponse getData() {
		return data;
	}

	public void setData(DataResponse data) {
		this.data = data;
	}
	
	
}
