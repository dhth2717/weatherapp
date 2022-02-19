package com.intuit.demo.weather.api

import com.intuit.demo.weather.domain.ApiError
import com.intuit.demo.weather.domain.Location
import com.intuit.demo.weather.exception.NoDataFoundException
import com.intuit.demo.weather.repository.HistTempRangeRepo
import com.intuit.demo.weather.service.LocationService
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

class LocationApiSpec extends Specification{


    private  LocationApi locationApi
    private LocationService locationServiceMock
    def setupSpec() {}
    def setup() {
        locationApi = new LocationApi();
        locationServiceMock = Mockito.mock(LocationService.class)
        ReflectionTestUtils.setField(locationApi, "locationSvc", locationServiceMock )
    }

    def "get Valid Location details"(){
        given:
            List<Location> locations = new ArrayList<>()
            Mockito.doReturn(locations).when(locationServiceMock).findAll()
        when:
            ResponseEntity<List<Location>> response = locationApi.getAllLocations()
        then:
            response.getStatusCode() == HttpStatus.OK
    }

    def "get NoDataFoundException for unavailability of monitored locations"(){
        given:
        List<Location> locations = new ArrayList<>()
        Mockito.doThrow(new NoDataFoundException("","")).when(locationServiceMock).findAll()
        when:
            ResponseEntity<ApiError> response = locationApi.getAllLocations()
        then:
            //thrown(NoDataFoundException)
            response.getStatusCode() == HttpStatus.OK
    }

    def cleanup() {}
    def cleanupSpec() {}


}
