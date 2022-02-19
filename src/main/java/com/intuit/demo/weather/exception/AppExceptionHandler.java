package com.intuit.demo.weather.exception;

import com.intuit.demo.weather.api.LocationApi;
import com.intuit.demo.weather.domain.ApiError;
import com.intuit.demo.weather.config.GlobalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * Global Exception handler to respond for error response
 */
@ControllerAdvice
public class AppExceptionHandler  {

    @Autowired
    private GlobalProperties globalProperties;

    Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    /**
     * Handle exceptions for NoDataFoundException.class , RuntimeException.class
     * @param ex - exception that is handled
     * @return
     */
    @ExceptionHandler({ NoDataFoundException.class , RuntimeException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        ApiError apiError ;
        HttpStatus status;
        if (ex instanceof NoDataFoundException) {
            Map<String, String> errConfig = globalProperties.getExceptions().get(((NoDataFoundException) ex).getCode());

             status = HttpStatus.valueOf(Integer.valueOf(errConfig.get("status")));
            apiError = new ApiError(errConfig.get("code"), errConfig.get("detail"),ex.getMessage(), ((NoDataFoundException) ex).getTime());
        } else if (ex instanceof ApplicationException) {
            Map<String, String> errConfig = globalProperties.getExceptions().get("INTERNAL_ERROR");
            errConfig.forEach((key, val)-> logger.debug( " handleException key:" +key  + " Value:"+ val));
             status = HttpStatus.valueOf(Integer.valueOf(errConfig.get("status")));
            apiError = new ApiError(errConfig.get("code"), errConfig.get("detail"),ex.getMessage(), ((ApplicationException)ex).getTime());
        } else {
            Map<String, String> errConfig = globalProperties.getExceptions().get("INTERNAL_ERROR");
            errConfig.forEach((key, val) -> logger.debug(" handleException key:" + key + " Value:" + val));
            status = HttpStatus.valueOf(Integer.valueOf(errConfig.get("status")));
            apiError = new ApiError(errConfig.get("code"), errConfig.get("detail"),ex.getMessage(), (LocalDateTime.now()));
        }
        return handleExceptionInternal(apiError, headers, status);
    }

    /**
     * Handling of internal error response.
     * @param body
     * @param headers
     * @param status
     * @return
     */
    protected ResponseEntity<ApiError> handleExceptionInternal(ApiError body, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<>(body, headers, status);
    }


}
