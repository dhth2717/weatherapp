package com.intuit.demo.weather.domain.custom;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Data
public class HistGlobalTempRange implements Serializable {

    private Float lowestTemp;
    private Float highestTemp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd HH:mm")
    private LocalDateTime endTime;

    public HistGlobalTempRange(Float lowestTemp, Float highestTemp) {
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
//        this.startTime = startTime;
//        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "HistGlobalTempRange{" +
                "lowestTemp=" + lowestTemp +
                ", highestTemp=" + highestTemp +
                '}';
    }
}
