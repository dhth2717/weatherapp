package com.intuit.demo.weather.service

import com.intuit.demo.weather.domain.HistAvgTemp
import com.intuit.demo.weather.domain.Location
import com.intuit.demo.weather.exception.NoDataFoundException
import com.intuit.demo.weather.repository.HistAvgTempRepo
import com.intuit.demo.weather.repository.HistTempRangeRepo
import com.intuit.demo.weather.repository.LocationRepo
import org.mockito.Mockito
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

import java.time.LocalDateTime

class TemperatureServiceSpec extends Specification{

    private  TemperatureService temperatureService
    def locationRepoMock
    def histAvgTempRepoMock
    def histTempRangeRepoMock
    def locationLst

    def setupSpec(){
    }

    def setup(){
        temperatureService = new TemperatureService()
        locationRepoMock = Mockito.mock(LocationRepo.class)
        histAvgTempRepoMock = Mockito.mock(HistAvgTempRepo.class)
        histTempRangeRepoMock = Mockito.mock(HistTempRangeRepo.class)
        ReflectionTestUtils.setField(temperatureService, "locationRepo", locationRepoMock )
        ReflectionTestUtils.setField(temperatureService, "histAvgTempRepo", histAvgTempRepoMock )
        ReflectionTestUtils.setField(temperatureService, "histTempRangeRepo", histTempRangeRepoMock )
    }

    def "get valid average temp for valid location and time" () {
        given:
            Optional<Location> location = Optional.of( new Location())
            Mockito.doReturn(location).when(locationRepoMock).findByLatitudeAndLongitude(Mockito.anyDouble() , Mockito.anyDouble())
            Optional<HistAvgTemp> histAvgTemp = Optional.of( new HistAvgTemp())
            Mockito.doReturn(histAvgTemp).when(histAvgTempRepoMock).findByLocAndMonthDayAndDayTime(Mockito.any(), Mockito.any(), Mockito.any())
        when:
            def response = temperatureService.getAvgTempByLocDateTime("90", "180", "01-01 01")
        then:
            response == histAvgTemp.get()
    }

    def "get NoDataFoundException for invalid location" () {
        given:
        Optional<Location> location = Optional.empty()
        Mockito.doReturn(location).when(locationRepoMock).findByLatitudeAndLongitude(Mockito.anyDouble() , Mockito.anyDouble())
        Optional<HistAvgTemp> histAvgTemp = Optional.of( new HistAvgTemp())
        Mockito.doReturn(histAvgTemp).when(histAvgTempRepoMock).findByLocAndMonthDayAndDayTime(Mockito.any(), Mockito.any(), Mockito.any())
        when:
        def response = temperatureService.getAvgTempByLocDateTime("180", "180", "01-01 01")
        then:
            thrown(NoDataFoundException)
    }

    def "get NoDataFoundException for invalid  time" () {
        given:
        Optional<Location> location = Optional.of( new Location())
        Mockito.doReturn(location).when(locationRepoMock).findByLatitudeAndLongitude(Mockito.anyDouble() , Mockito.anyDouble())
        Optional<HistAvgTemp> histAvgTemp = Optional.empty()
        Mockito.doReturn(histAvgTemp).when(histAvgTempRepoMock).findByLocAndMonthDayAndDayTime(Mockito.any(), Mockito.any(), Mockito.any())
        when:
        def response = temperatureService.getAvgTempByLocDateTime("90", "180", "01-01 01")
        then:
        thrown(NoDataFoundException)
    }


    def cleanup(){
    }

    def cleanupSpec(){
    }

}
