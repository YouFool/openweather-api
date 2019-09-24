package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.CityRepository;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Service responsible to manage user-tracked cities.
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private OpenWeatherCityRepository openWeatherCityRepository;

    /**
     * Finds and returns all cities.
     * TODO: make it pageable
     * TODO: return basic weather data in the output
     *
     * @return a {@link List} of {@link City} user-inputted cities
     */
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    /**
     * Creates and links a city with a given name and it's country code to an existing OpenWeather city.
     *
     * @param cityName    the city name
     * @param countryCode the city country code
     * @return the created {@link City}
     * @throws ResponseStatusException if city does not exists
     */
    public City createCity(String cityName, String countryCode) {
        List<OpenWeatherCity> citiesByNameAndCountry = this.openWeatherCityRepository.findByNameAndCountry(cityName, countryCode);

        OpenWeatherCity openWeatherCity = citiesByNameAndCountry.stream()
                .findFirst()
                .orElse(null);

        if (CollectionUtils.isEmpty(citiesByNameAndCountry) || openWeatherCity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City + '" + cityName + "' not found!");
        }

        City cityToSave = new City(cityName, countryCode, Long.valueOf(openWeatherCity.getId()));

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
