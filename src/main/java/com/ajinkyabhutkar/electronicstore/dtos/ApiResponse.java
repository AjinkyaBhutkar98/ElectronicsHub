package com.ajinkyabhutkar.electronicstore.dtos;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private String messege;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiResponse() {
    }

    public ApiResponse(String messege, boolean success, HttpStatus httpStatus) {
        this.messege = messege;
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
