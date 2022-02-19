package com.intuit.demo.weather.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="LOCATION", schema="weatherdb")
@Data
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOC_ID")
    @JsonIgnore
    private long locId;

    @Column
    @NotBlank(message="The latitude is required.")
    @Min(value = -90, message = "latitude should not be less than -90")
    @Max(value = 90, message = "latitude should not be greater than 90")
    private Double latitude;

    @Column
    @NotBlank(message="The longitude is required.")
    @Min(value = -180, message = "longitude should not be less than -180")
    @Max(value = 180, message = "longitude should not be greater than 180")
    private Double longitude;

    @Column
    private String description;

    @OneToMany(mappedBy="location")
    @JsonIgnore
    private Set<HistAvgTemp> locAvgTemps;


}
