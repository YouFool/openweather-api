package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.configuration.CityConfiguration;
import br.com.hbsis.openweather.converter.OpenWeatherCityConverter;
import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import br.com.hbsis.openweather.util.Messages;
import br.com.hbsis.openweather.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Service responsible for the communication with the OpenWeather API.
 */
@Service
public class OpenWeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherService.class);

    @Value("${openweather.api.key}")
    private String openWeatherApiKey;

    @Value("${openweather.api.baseurl}")
    private String openWeatherApiUrl;

    private OpenWeatherCityRepository openWeatherCityRepository;

    private CityService cityService;

    private CityConfiguration cityConfiguration;

    private OpenWeatherCityConverter openWeatherCityConverter;

    @Autowired
    public OpenWeatherService(OpenWeatherCityRepository openWeatherCityRepository, CityService cityService, CityConfiguration cityConfiguration, OpenWeatherCityConverter openWeatherCityConverter) {
        this.openWeatherCityRepository = openWeatherCityRepository;
        this.cityService = cityService;
        this.cityConfiguration = cityConfiguration;
        this.openWeatherCityConverter = openWeatherCityConverter;
    }

    /**
     * Reads a file containing all OpenWeather cities and saves all entities in a MongoDB collection.
     */
    public void createAllCities() {
        List<OpenWeatherCity> openWeatherCities = this.cityConfiguration.readCities();
        this.openWeatherCityRepository.saveAll(openWeatherCities);
        LOGGER.info("OpenWeather cities are now saved!");
    }

    /**
     * Finds a {@link City} and then requests city-weather data from OpenWeather API.
     *
     * @param cityId the user-inputted city ID
     * @return a {@link OpenWeatherCityDTO} containing the city weather data
     */
    public OpenWeatherCityDTO getCityWeather(Long cityId) {
        City city = this.cityService.findCityByIdThrowsException(cityId);

        RestTemplate weatherRestTemplate = new RestTemplate();
        final String weatherApiUrl = this.mountRequestUrl(city.getOpenWeatherId().toString(), false);
        return weatherRestTemplate.getForObject(weatherApiUrl, OpenWeatherCityDTO.class);
    }

    /**
     * Finds a {@link City} and then requests a 5-day forecast from OpenWeather API.
     *
     * @param cityId the user-inputted city ID
     * @return a {@link ForecastDTO} 5-day forecast
     * @throws ResponseStatusException if response body is null
     */
    public ForecastDTO getCityForecast(Long cityId) {
        City city = this.cityService.findCityByIdThrowsException(cityId);

        RestTemplate forecastRestTemplate = new RestTemplate();
        final String forecastApiUrl = this.mountRequestUrl(city.getOpenWeatherId().toString(), true);
        ForecastDTO forecastDTO = forecastRestTemplate.getForObject(forecastApiUrl, ForecastDTO.class, cityId);

        if (forecastDTO == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    Translator.translateMessage(Messages.ERROR_FAILED_TO_FETCH_API_DATA));
        }
        return this.openWeatherCityConverter.groupForecastsByDate(forecastDTO);
    }

    /**
     * Mounts the URL to the corresponding OpenWeather API.
     *
     * @param cityId the OpenWeather city ID
     * @return the URL
     */
    private String mountRequestUrl(String cityId, boolean isForecast) {
        String apiPath = isForecast ? "/forecast" : "/weather";
        return openWeatherApiUrl + apiPath +
                "?id=" + cityId +
                "&units=metric" +
                "&APPID=" + this.openWeatherApiKey;
    }
}
