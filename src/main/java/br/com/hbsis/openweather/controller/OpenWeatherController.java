package br.com.hbsis.openweather.controller;

import br.com.hbsis.openweather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible to OpenWeather API requests.
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
    public Void getCityWeather(Long cityId) {
        return null;
    }

    /**
     * Gets the city 5-day forecast.
     *
     * @param cityId the city id
     * @return
     */
    public Void getCityForecast(Long cityId) {
        return null;
    }


}
