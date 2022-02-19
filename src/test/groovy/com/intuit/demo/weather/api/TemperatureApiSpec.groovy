package com.intuit.demo.weather.api

import com.intuit.demo.weather.domain.HistAvgTemp
import com.intuit.demo.weather.domain.HistTempRange
import com.intuit.demo.weather.domain.custom.HistGlobalTempRange
import com.intuit.demo.weather.exception.NoDataFoundException
import com.intuit.demo.weather.service.LocationService
import com.intuit.demo.weather.service.TemperatureService
import com.sun.corba.se.impl.orbutil.graph.NodeData
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

class TemperatureApiSpec extends Specification{

    private  TemperatureApi temperatureApi
    private TemperatureService temperatureServiceMock
    private HistAvgTemp histAvgTempMock
    private HistGlobalTempRange histGlobalTempRangeMock

    def setupSpec() {}

    def setup() {
        temperatureApi = new TemperatureApi();
        temperatureServiceMock = Mockito.mock(TemperatureService.class)
        histAvgTempMock = Mockito.mock(HistAvgTemp.class)
        histGlobalTempRangeMock = Mockito.mock(HistGlobalTempRange.class)
        ReflectionTestUtils.setField(temperatureApi, "tempSvc", temperatureServiceMock )
    }
    //getAvgTempForLocByTime

    def "get valid average temp for location and time"(){
        given:
        Mockito.doReturn(histAvgTempMock).when(temperatureServiceMock).getAvgTempByLocDateTime(Mockito.any(),Mockito.any(),Mockito.any())
        when:
        ResponseEntity<HistAvgTemp> response =temperatureApi.getAvgTempForLocByTime(Mockito.any(),Mockito.any(),Mockito.any())
        then:
            response.getStatusCode() == HttpStatus.OK
    }

    def "get NoDataFound for invalid location/time"(){
        given:
            Mockito.doThrow(new NoDataFoundException(Mockito.any(), Mockito.any())).when(temperatureServiceMock).getAvgTempByLocDateTime(Mockito.any(),Mockito.any(),Mockito.any())
        when:
        ResponseEntity<HistAvgTemp> response =temperatureApi.getAvgTempForLocByTime(Mockito.any(),Mockito.any(),Mockito.any())
        then:
        response.getStatusCode() == HttpStatus.NOT_FOUND
    }

    def "get valid global temp range for time"(){
        given:
        Mockito.doReturn(histGlobalTempRangeMock).when(temperatureServiceMock).getGlobalTempRange(Mockito.any(),Mockito.any())
        when:
        ResponseEntity<HistGlobalTempRange> response =temperatureApi.getGlobalTempRange(Mockito.any(),Mockito.any())
        then:
        response.getStatusCode() == HttpStatus.OK
    }

    def "get NoDataFound for invalid input time range"(){
        given:
        Mockito.doThrow(new NoDataFoundException(Mockito.any(), Mockito.any())).when(temperatureServiceMock).getGlobalTempRange(Mockito.any(),Mockito.any())
        when:
        ResponseEntity<HistAvgTemp> response =temperatureApi.getGlobalTempRange(Mockito.any(),Mockito.any())
        then:
        response.getStatusCode() == HttpStatus.NOT_FOUND
    }

    def cleanup() {}
    def cleanupSpec() {}
}
