package com.ajinkyabhutkar.electronicstore.dtos;

import org.springframework.http.HttpStatus;

public class FileResponse {

    private String fileName;
    private String messege;
    private boolean success;
    private HttpStatus httpStatus;

    public FileResponse() {
    }

    public FileResponse(String fileName, String messege, boolean success, HttpStatus httpStatus) {
        this.fileName = fileName;
        this.messege = messege;
        this.success = success;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
