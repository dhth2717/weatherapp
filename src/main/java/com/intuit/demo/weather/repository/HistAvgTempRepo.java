package com.intuit.demo.weather.repository;

import com.intuit.demo.weather.domain.HistAvgTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 *
 */
@Repository
public interface HistAvgTempRepo extends JpaRepository<HistAvgTemp, Long> {

    @Query("SELECT hist FROM HistAvgTemp hist WHERE hist.location.latitude= :lats AND hist.location.longitude= :longt AND hist.dateTime= :dateTime ")
   Optional<HistAvgTemp>  findByLocAndMonthDayAndDayTime(@Param("lats")  Double lats , @Param("longt")  Double longt , @Param("dateTime") LocalDateTime dateTime );



}
