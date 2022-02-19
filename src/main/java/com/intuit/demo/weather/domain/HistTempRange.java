package com.intuit.demo.weather.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="HIST_TEMP_RANGE", schema="weatherdb")
@Data
public class HistTempRange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long temprange_id;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd HH:mm")
    private LocalDateTime dateTime;
    @Column
    private Float lowestTemp;
    @Column
    private Float highestTemp;
}
