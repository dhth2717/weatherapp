package com.intuit.demo.weather.repository;

import com.intuit.demo.weather.domain.HistTempRange;
import com.intuit.demo.weather.domain.custom.HistGlobalTempRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface HistTempRangeRepo extends JpaRepository<HistTempRange, Long> {

    @Query("SELECT new com.intuit.demo.weather.domain.custom.HistGlobalTempRange (max(hist.highestTemp) , min(hist.lowestTemp) ) FROM HistTempRange hist WHERE hist.dateTime >= :startTime and hist.dateTime < :endTime ")
    Optional<HistGlobalTempRange> findHistTempRangeForTime(@Param("startTime") LocalDateTime startTime , @Param("endTime") LocalDateTime endTime );




}
