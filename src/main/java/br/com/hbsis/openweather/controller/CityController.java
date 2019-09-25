package br.com.hbsis.openweather.controller;

import br.com.hbsis.openweather.dto.CityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller responsible to city-related endpoints.
 */
@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Returns all user-persisted cities.
     *
     * @return list with {@link City} cities
     */
    @GetMapping
    public List<City> listCities() {
        return this.cityService.findAll();
    }

    /**
     * Creates a {@link City} and links it with an OpenWeather city.
     *
     * @param city {@link CityDTO} city to create
     * @return the created {@link City}
     */
    @PostMapping
    public City createCity(@RequestBody CityDTO city) {
        return this.cityService.createCity(city.getName(), city.getCountryCode());
    }

    /**
     * Deletes a city by it's ID.
     *
     * @param cityId the city id
     */
    @DeleteMapping(path = "/{cityId}")
    public void deleteCity(@PathVariable Long cityId) {
        City city = this.cityService.findCityByIdThrowsException(cityId);
        this.cityService.deleteCity(city);
    }

}
