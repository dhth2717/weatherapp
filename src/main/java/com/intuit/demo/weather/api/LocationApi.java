package com.intuit.demo.weather.api;

import com.intuit.demo.weather.domain.ApiError;
import com.intuit.demo.weather.domain.HistAvgTemp;
import com.intuit.demo.weather.domain.Location;
import com.intuit.demo.weather.exception.NoDataFoundException;
import com.intuit.demo.weather.service.LocationService;
import com.intuit.demo.weather.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("LocationApi")
@RequestMapping(value = "/v1/locations")
@Api(value = "Location api related details of weather", description = "API to get location related details where weather is monitored")
public class LocationApi {

    @Autowired
    private LocationService locationSvc;

    Logger logger = LoggerFactory.getLogger(LocationApi.class);

    @ApiOperation(value = "Get all location details that are managed for weather monitoring. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Location.class),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST, response = ApiError.class),
            @ApiResponse(code = 404, message = AppConstants.NO_DATA_FOUND, response = ApiError.class),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_ERROR, response = ApiError.class) })
    @GetMapping("")
    public ResponseEntity<List<Location>> getAllLocations() throws NoDataFoundException {
        List<Location> locations =  locationSvc.getAllLocations();

        return new ResponseEntity<>(locations , HttpStatus.OK);
    }



}
