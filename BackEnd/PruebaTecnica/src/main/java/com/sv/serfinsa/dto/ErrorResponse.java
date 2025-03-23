package com.sv.serfinsa.dto;

public class ErrorResponse {
	
    private String code;

    private String title;

    private String detail;

    public ErrorResponse() {
    }

    public ErrorResponse(String code, String title, String detail) {
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    

}
