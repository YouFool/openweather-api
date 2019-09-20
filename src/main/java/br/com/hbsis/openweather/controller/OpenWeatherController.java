package br.com.hbsis.openweather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OpenWeatherController {

    //TODO: CRUD operations on a given city
    public Void createCity() {
        return null;
    }

    //TODO: get a city details and 5-day forecast
    public Void getCityWeather(Integer cityId) {
        return null;
    }


}
