package com.intuit.demo.weather.service

import com.intuit.demo.weather.domain.Location
import com.intuit.demo.weather.exception.NoDataFoundException
import com.intuit.demo.weather.repository.LocationRepo
import org.mockito.Mockito
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

public class LocationServiceSpec extends Specification{

    private  LocationService locationService
    def locationRepoMock
    def locationLst

    def setupSpec(){
    }

    def setup(){
        locationService = new LocationService()
        locationRepoMock = Mockito.mock(LocationRepo.class)
        ReflectionTestUtils.setField(locationService, "locationRepo", locationRepoMock )
    }


    def "get valid location response for valid lat , long"(){
        given:
        Location location = new Location ();
        location.setLocId(1)
        location.setLatitude(90)
        location.setLongitude(180)
        location.setDescription("Dublin")
        locationLst = new ArrayList()
        locationLst.add(location)
        Mockito.doReturn(locationLst).when(locationRepoMock).findAll()

        when:
            def response = locationService.getAllLocations()
        then:
          response.size() == 1
          response.get(0).getDescription() == "Dublin"
    }

    def "throw NoDataFoundException when no data found"(){
        given:
        locationLst = new ArrayList()
        Mockito.doReturn(locationLst).when(locationRepoMock).findAll()
        when:
        def response = locationService.getAllLocations()
        then:
            thrown(NoDataFoundException)
        }

    def cleanup(){
    }

    def cleanupSpec(){
    }

}
