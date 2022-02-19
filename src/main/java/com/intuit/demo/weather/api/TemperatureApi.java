package com.intuit.demo.weather.api;


import com.intuit.demo.weather.domain.ApiError;
import com.intuit.demo.weather.domain.HistAvgTemp;
import com.intuit.demo.weather.domain.custom.HistGlobalTempRange;
import com.intuit.demo.weather.exception.NoDataFoundException;
import com.intuit.demo.weather.service.TemperatureService;
import com.intuit.demo.weather.util.AppConstants;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("TemperatureApi")
@RequestMapping(value = "/v1/temperature")
@Api(value = "Temperature api related details of weather", description = "API to get temperature related details of weather")
public class TemperatureApi {

    @Autowired
    private TemperatureService tempSvc ;

    Logger logger = LoggerFactory.getLogger(TemperatureApi.class);

    @ApiOperation(value = "Get Average Temperature of a location for required time ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = HistAvgTemp.class),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST, response = ApiError.class),
            @ApiResponse(code = 404, message = AppConstants.NO_DATA_FOUND, response = ApiError.class),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_ERROR, response = ApiError.class) })
    @GetMapping("/average")
    public ResponseEntity<HistAvgTemp> getAvgTempForLocByTime(@ApiParam(value = "Latitude coordinate of location values between(-90, 90)", required = true, name = "latitude") @RequestParam("latitude") String latitude,
                                                              @ApiParam(value = "Longitude coordinate of location  values between(-180, 180)", required = true, name = "longitude") @RequestParam("longitude") String longitude,
                                                              @ApiParam(value = "Time providing Month, day and hour in format MM-DD HH", required = true, name = "time")  @RequestParam("time") String time) throws NoDataFoundException {
        HistAvgTemp avgTemp = tempSvc.getAvgTempByLocDateTime(latitude , longitude,  time);
        return new ResponseEntity<>(avgTemp , HttpStatus.OK);


    }

    @ApiOperation(value = "Get Global Temperature Range for time duration ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = HistGlobalTempRange.class),
            @ApiResponse(code = 400, message = AppConstants.BAD_REQUEST, response = ApiError.class),
            @ApiResponse(code = 404, message = AppConstants.NO_DATA_FOUND, response = ApiError.class),
            @ApiResponse(code = 500, message = AppConstants.INTERNAL_ERROR, response = ApiError.class) })
    @GetMapping(value="/globalrange" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistGlobalTempRange> getGlobalTempRange( @ApiParam(value = "Start Time providing Month, day and hour in format MM-DD HH", required = true, name = "startTime") @RequestParam("startTime") String startTime,
                                                                   @ApiParam(value = "End Time providing Month, day and hour in format MM-DD HH", required = true, name = "ednTime") @RequestParam("endTime") String endTime) throws NoDataFoundException{
        HistGlobalTempRange histGlobalTempRange = tempSvc.getGlobalTempRange(startTime ,endTime);
        return new ResponseEntity<>(histGlobalTempRange , HttpStatus.OK);
    }


}
