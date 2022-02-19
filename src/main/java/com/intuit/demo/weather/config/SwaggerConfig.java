package com.intuit.demo.weather.config;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@ConfigurationProperties(prefix="application")
@ConditionalOnProperty(name="application.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {


    @Bean
    public Docket api() {

       Docket docket =   new Docket(DocumentationType.SWAGGER_2).enable(true).select()
			   .apis(RequestHandlerSelectors.basePackage("com.intuit.demo.weather.api")).paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	   return docket;
    }

	private ApiInfo getApiInfo() {


		return new ApiInfo("Weather Application",
				"Application to get weather forecast for temperature across the globe", "1.0",
				"urn:tos", new Contact("weatherapisupportgroup", "www.weatherapp.com", ""), "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0");
	}

}
