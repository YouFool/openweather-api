package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.configuration.CityConfiguration;
import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service responsible for the communication with the OpenWeather API.
 */
@Service
public class OpenWeatherService {

    @Value("${openweather.api.key}")
    private String openWeatherApiKey;

    @Value("${openweather.api.baseurl}")
    private String openWeatherApiUrl;

    @Autowired
    private OpenWeatherCityRepository openWeatherCityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityConfiguration cityConfiguration;

    /**
     * Reads a file containing all OpenWeather cities and saves all entities in a MongoDB collection.
     */
    public void createAllCities() {
        List<OpenWeatherCity> openWeatherCities = this.cityConfiguration.readCities();
        this.openWeatherCityRepository.saveAll(openWeatherCities);
        System.err.println("OpenWeather cities are now saved!");
    }

    /**
     * Finds an OpenWeather city by it's ID.
     *
     * @param cityId the OpenWeather city id
     * @return an OpenWeather city
     * @throws ResponseStatusException if city is not found
     */
    private OpenWeatherCity findOpenWeatherCity(Long cityId) {
        return this.openWeatherCityRepository.findById(String.valueOf(cityId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found!"));
    }

    /**
     * Finds a {@link City} and then requests city-weather data from OpenWeather API.
     *
     * @param cityId the user-inputted city ID
     * @return a {@link OpenWeatherCityDTO} DTO containing the city weather data
     * @throws ResponseStatusException if the city is not found
     */
    public OpenWeatherCityDTO getCityWeather(Long cityId) {
        City city = this.cityService.findCity(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id: '" + cityId + "' not found!"));

        RestTemplate weatherRestTemplate = new RestTemplate();
        final String weatherApiUrl = this.mountUrl(city.getOpenWeatherId().toString(), false);
        return weatherRestTemplate.getForObject(weatherApiUrl, OpenWeatherCityDTO.class);
    }

    /**
     * Finds a {@link City} and then requests a 5-day forecast from OpenWeather API.
     *
     * @param cityId the user-inputted city ID
     * @return a {@link ForecastDTO} 5-day forecast TODO: maybe i'll need to convert/group it
     * @throws ResponseStatusException if the city is not found
     */
    public ForecastDTO getCityForecast(Long cityId) {
        City city = this.cityService.findCity(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id: '" + cityId + "' not found!"));

        RestTemplate forecastRestTemplate = new RestTemplate();
        final String forecastApiUrl = this.mountUrl(city.getOpenWeatherId().toString(), true);
        return forecastRestTemplate.getForObject(forecastApiUrl, ForecastDTO.class, cityId);
    }

    /**
     * Mounts the URL to the corresponding OpenWeather API.
     *
     * @param cityId the OpenWeather city ID
     * @return the URL
     */
    private String mountUrl(String cityId, boolean isForecast) {
        String apiPath = isForecast ? "/forecast" : "/weather" ;
        return openWeatherApiUrl + apiPath +
                "?id=" + cityId +
                "&units=metric" +
                "&APPID=" + this.openWeatherApiKey;
    }
}
