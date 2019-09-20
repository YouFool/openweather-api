package br.com.hbsis.openweather.controller;

import br.com.hbsis.openweather.dto.CityDTO;
import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Rest controller responsible to city-related endpoints.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

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
     * Creates a city.
     *
     * @param city City to create
     * @return
     */
    @PostMapping
    public City createCity(@RequestBody CityDTO city) {
        final String capitalizedCityName = StringUtils.capitalize(city.getName().toLowerCase());
        final String countryPrefix = city.getCountryCode();

        return this.cityService.createCity(capitalizedCityName, countryPrefix);
    }

    /**
     * Deletes a city by it's ID.
     *
     * @param cityId the city id
     */
    @DeleteMapping(path = "/{cityId}")
    public String deleteCity(@PathVariable Long cityId) {
        City city = this.cityService.findCity(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found!"));

        this.cityService.deleteCity(city);
        return "City '" + city.getName() + "' has been removed!";
    }

}
