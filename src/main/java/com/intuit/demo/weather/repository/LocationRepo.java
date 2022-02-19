package com.intuit.demo.weather.repository;

import com.intuit.demo.weather.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 */
@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */

    Optional<Location> findByLocId(long locId);
    public Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
