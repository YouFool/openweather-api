package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.configuration.OpenweatherConfiguration;
import br.com.hbsis.openweather.converter.OpenWeatherCityConverter;
import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.util.Messages;
import br.com.hbsis.openweather.util.Translator;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service responsible for the communication with the OpenWeather API.
 */
@Service
@CommonsLog
public class OpenWeatherService {

    private final CityService cityService;
    private final OpenWeatherCityConverter openWeatherCityConverter;
    private final OpenweatherConfiguration openweatherConfiguration;

    @Autowired
    public OpenWeatherService(CityService cityService, OpenWeatherCityConverter openWeatherCityConverter, OpenweatherConfiguration openweatherConfiguration) {
        this.cityService = cityService;
        this.openWeatherCityConverter = openWeatherCityConverter;
        this.openweatherConfiguration = openweatherConfiguration;
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
        final String weatherApiUrl = this.mountRequestUrl(city.getOpenWeatherId(), false);
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
        final String forecastApiUrl = this.mountRequestUrl(city.getOpenWeatherId(), true);
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
        return this.openweatherConfiguration.getApi().getBaseUrl() + apiPath +
                "?id=" + cityId +
                "&units=metric" +
                "&APPID=" + this.openweatherConfiguration.getApi().getKey();
    }
}
