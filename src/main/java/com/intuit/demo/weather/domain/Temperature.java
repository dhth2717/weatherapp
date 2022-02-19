package com.intuit.demo.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="temperature")
@Getter
@Setter
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String location;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column
    private String temperature;
}
