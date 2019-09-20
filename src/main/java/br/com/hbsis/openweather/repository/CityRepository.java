package br.com.hbsis.openweather.repository;

import br.com.hbsis.openweather.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for user-inputted cities.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
