package br.com.hbsis.openweather.controller;

import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * Rest controller responsible to manage OpenWeather API requests.
 */
@RestController
@RequestMapping("/weather")
@CrossOrigin
public class OpenWeatherController {

    private final OpenWeatherService openWeatherService;

    @Autowired
    public OpenWeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    /**
     * Returns the weather details of a given {@link City}.
     *
     * @param cityId the city ID
     * @return a {@link OpenWeatherCityDTO} containing weather data
     */
    @GetMapping("/{cityId}")
    public OpenWeatherCityDTO getCityWeather(@PathVariable Long cityId) {
        return this.openWeatherService.getCityWeather(cityId);
    }

    /**
     * Returns a 5-day forecast of a given {@link City}.
     *
     * @param cityId the city ID
     * @return a {@link ForecastDTO} containing forecast data
     */
    @GetMapping("/forecast/{cityId}")
    public ForecastDTO getCityForecast(@PathVariable Long cityId) {
        return this.openWeatherService.getCityForecast(cityId);
    }


}
