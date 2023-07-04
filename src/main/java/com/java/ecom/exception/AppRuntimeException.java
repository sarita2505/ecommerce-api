package com.java.ecom.exception;

public class AppRuntimeException extends RuntimeException {
    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(Throwable e, String errorMessage , String... args){
        super(e);
    }
}
