package com.intuit.demo.weather.service;

import com.intuit.demo.weather.domain.HistAvgTemp;
import com.intuit.demo.weather.domain.Location;
import com.intuit.demo.weather.domain.custom.HistGlobalTempRange;
import com.intuit.demo.weather.exception.AppExceptionHandler;
import com.intuit.demo.weather.exception.NoDataFoundException;
import com.intuit.demo.weather.repository.HistAvgTempRepo;
import com.intuit.demo.weather.repository.HistTempRangeRepo;
import com.intuit.demo.weather.repository.LocationRepo;
import com.intuit.demo.weather.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 *
 */
@Service
public class TemperatureService {

    @Autowired
    private HistAvgTempRepo histAvgTempRepo;

    @Autowired
    private HistTempRangeRepo histTempRangeRepo;

    @Autowired
    private LocationRepo locationRepo;

    Logger logger = LoggerFactory.getLogger(TemperatureService.class);

    /**
     *Get the average temperature for the location and time
     * @param latitude
     * @param longitude
     * @param dateTime
     * @return
     * @throws NoDataFoundException
     */
    public HistAvgTemp getAvgTempByLocDateTime(String latitude, String longitude, String dateTime) throws NoDataFoundException {

        // Check for location to be valid
         Optional<Location> Location = locationRepo.findByLatitudeAndLongitude(Double.valueOf(latitude) , Double.valueOf(longitude));
         if(!Location.isPresent()) {
             String message = new StringBuilder("Location with coordinates ") .append(latitude).append(" ").append(longitude).append(" not currently monitored by the systems.").toString();
             logger.error(new StringBuilder("No Location found about the location detail : latitude/longitude :").append(latitude).append("/").append(longitude).toString());
             throw new NoDataFoundException("BAD_REQUEST", message);
         }

        // Get the average temperature for the location and time
        LocalDateTime inDateTime = CommonUtil.getDateTimeFromStr(dateTime);
        System.out.println( "Input :");
        System.out.println( "Input lats :" + Double.valueOf(latitude));
        System.out.println( "Input long :" + Double.valueOf(longitude));
        System.out.println( "Input DateTime  :" + inDateTime.toString());
        Optional<HistAvgTemp> optAvgTemp = histAvgTempRepo.findByLocAndMonthDayAndDayTime(Double.valueOf(latitude), Double.valueOf(longitude) , inDateTime );
        if(!optAvgTemp.isPresent()) {
            logger.error(new StringBuilder("No historical data found about the location detail : latitude/longitude/datetime :").append(latitude).append("/").append(longitude).append("/").append(dateTime).toString());
            throw new NoDataFoundException("NO_DATA_FOUND" , "No historical data found about the location");
        }
        return optAvgTemp.get();

    }

    /**
     * Get the Global Temperature for provided time range ( MM:DD HH)
     * @param start  - start time range
     * @param end   -  end time range
     * @return
     * @throws NoDataFoundException
     */
    public HistGlobalTempRange getGlobalTempRange(String start, String end) throws NoDataFoundException {

        // Split the start Time string to get the month, day and hour values
        LocalDateTime startTime = CommonUtil.getDateTimeFromStr(start);
        LocalDateTime endTime = CommonUtil.getDateTimeFromStr(end);

        Optional<HistGlobalTempRange> globalTempRange = histTempRangeRepo.findHistTempRangeForTime(startTime ,endTime);
        logger.debug("Global Temp Range is "+ globalTempRange.get());
        if(!globalTempRange.isPresent()) {
            String message = new StringBuilder("No historical data found for the time range provided.").append(startTime).append("-").append(endTime).toString();
            throw new NoDataFoundException("NO_DATA_FOUND" , message);
        }
        return  globalTempRange.get();

    }

}
