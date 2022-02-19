package com.intuit.demo.weather.exception;

public class NoDataFoundException extends ApplicationException{

    public NoDataFoundException(String code, String message) {
        super(code, message);
    }
}
