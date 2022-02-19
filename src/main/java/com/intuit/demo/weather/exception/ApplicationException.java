package com.intuit.demo.weather.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ApplicationException extends Exception{

    private String code ;
    private String message ;
    private LocalDateTime time;

    public ApplicationException(String code , String message){
        this.code = code;
        this.message = message;
        this.time =  LocalDateTime.now();
    }
}


