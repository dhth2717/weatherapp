package com.intuit.demo.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {

    private String code ;
    private String detail ;
    private String message ;
    private LocalDateTime time;

}
