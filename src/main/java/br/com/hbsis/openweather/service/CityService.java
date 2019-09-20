package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service responsible to manage cities.
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    /**
     * Finds and returns all cities.
     * TODO: make it pageable
     *
     * @return
     */
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    /**
     * Creates a city with a given name and it's country code.
     *
     * @param cityName    the city name
     * @param countryCode the city country code
     * @return
     */
    public City createCity(String cityName, String countryCode) {
        //TODO: validate here if city exists at all, we can do this via MongoDB query

        City cityToSave = new City(cityName, countryCode);

        return this.cityRepository.save(cityToSave);
    }

    /**
     * Deletes a city.
     *
     * @param city the city to be deleted
     */
    public void deleteCity(City city) {
        this.cityRepository.delete(city);
    }

    /**
     * Finds a city by it's ID.
     *
     * @param cityId the city id
     * @return an {@link Optional} city
     */
    public Optional<City> findCity(Long cityId) {
        return Optional.of(this.cityRepository.getOne(cityId));
    }
}
