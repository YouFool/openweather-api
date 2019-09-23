package br.com.hbsis.openweather.controller;

import br.com.hbsis.openweather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller responsible to manage OpenWeather API requests.
 */
@RestController
@RequestMapping("/weather")
public class OpenWeatherController {

    @Autowired
    private OpenWeatherService openWeatherService;

    /**
     * Gets the city weather details.
     *
     * @param cityId the city id
     * @return
     */
    @GetMapping(path = "/city/{cityId}")
    public Void getCityWeather(@PathVariable Long cityId) {
        return this.openWeatherService.getCityWeather(cityId);
    }

    /**
     * Gets the city 5-day forecast.
     *
     * @param cityId the city id
     * @return
     */
    @GetMapping(path = "/forecast/{cityId}")
    public Void getCityForecast(@PathVariable Long cityId) {
        return this.openWeatherService.getCityForecast(cityId);
    }


}
