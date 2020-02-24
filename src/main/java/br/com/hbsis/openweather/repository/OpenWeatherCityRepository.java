package br.com.hbsis.openweather.repository;

import br.com.hbsis.openweather.entity.OpenWeatherCity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoDB repository for OpenWeather cities.
 */
public interface OpenWeatherCityRepository extends MongoRepository<OpenWeatherCity, String> {

    /**
     * Finds a city by it's name and country code.
     *
     * @param name    the city name
     * @param country city country code
     * @return the {@link OpenWeatherCity} or null
     */
    List<OpenWeatherCity> findByNameAndCountry(String name, String country);

}
