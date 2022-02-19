package com.intuit.demo.weather.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="`HIST_AVG_TEMP`", schema="weatherdb" )
@Data
public class HistAvgTemp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long avgtemp_id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd HH:mm")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "LOCATION", nullable = false)
    private Location location;

    @Column
    private Float avgTemp;


}
