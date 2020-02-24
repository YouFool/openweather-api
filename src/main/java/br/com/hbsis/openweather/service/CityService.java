package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.CityRepository;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import br.com.hbsis.openweather.util.Messages;
import br.com.hbsis.openweather.util.Translator;
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

    private final CityRepository cityRepository;
    private final OpenWeatherCityRepository openWeatherCityRepository;

    @Autowired
    public CityService(CityRepository cityRepository, OpenWeatherCityRepository openWeatherCityRepository) {
        this.cityRepository = cityRepository;
        this.openWeatherCityRepository = openWeatherCityRepository;
    }

    /**
     * Finds and returns all cities.
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    Translator.translateMessage(Messages.ERROR_CITY_WITH_NAME_NOT_FOUND, cityName));
        }

        City cityToSave = new City(cityName, countryCode, openWeatherCity.getId());

        return this.cityRepository.save(cityToSave);
    }

    /**
     * Deletes a {@link City}.
     *
     * @param city the city to be deleted
     */
    public void deleteCity(City city) {
        this.cityRepository.delete(city);
    }

    /**
     * Finds an {@link Optional} {@link City} by it's ID.
     *
     * @param cityId the city id
     * @return a city or empty result
     */
    private Optional<City> findCity(Long cityId) {
        return Optional.of(this.cityRepository.getOne(cityId));
    }

    /**
     * Find's a {@link City} by it's ID.
     *
     * @param cityId the city id
     * @return the city found
     * @throws ResponseStatusException if the city does not exists
     */
    public City findCityByIdThrowsException(Long cityId) {
        return this.findCity(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Translator.translateMessage(Messages.ERROR_CITY_WITH_ID_NOT_FOUND, cityId)));
    }
}
