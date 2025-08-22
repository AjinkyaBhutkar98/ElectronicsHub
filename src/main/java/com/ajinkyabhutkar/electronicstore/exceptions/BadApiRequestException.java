package com.ajinkyabhutkar.electronicstore.exceptions;

public class BadApiRequestException extends RuntimeException{

    public BadApiRequestException(){
        super();
    }

    public BadApiRequestException(String msg){
        super(msg);
    }
}
