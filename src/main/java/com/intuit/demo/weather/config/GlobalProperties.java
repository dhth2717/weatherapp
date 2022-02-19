package com.intuit.demo.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class GlobalProperties {

    private Map<String, Map<String,String>> exceptions;


}
