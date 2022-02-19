package com.intuit.demo.weather.service;

import com.intuit.demo.weather.domain.Location;
import com.intuit.demo.weather.exception.NoDataFoundException;
import com.intuit.demo.weather.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Location Service is used to handle all location related domain detail functionalities.
 */
@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    /**
     * Get all location details that are currently monitored for weather data
     * @return
     * @throws NoDataFoundException
     */
    public List<Location> getAllLocations() throws NoDataFoundException{
        List<Location>  locationList = locationRepo.findAll();
        if(locationList.isEmpty())
            throw new NoDataFoundException("NO_DATA_FOUND" , "No location details found.");
        return  locationList;

    }
}
