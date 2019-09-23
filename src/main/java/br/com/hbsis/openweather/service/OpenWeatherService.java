package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.configuration.CityConfiguration;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
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
    private CityConfiguration cityConfiguration;

    /**
     * Reads a file containing all OpenWeather cities and saves all entities in MongoDB.
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
     * TODO: doc
     * Fetchs a given city weather data.
     *
     * @param cityId
     * @return
     */
    public Void getCityWeather(Long cityId) {
        //OpenWeatherCity openWeatherCity = this.findOpenWeatherCity(cityId);

        RestTemplate weatherRestTemplate = new RestTemplate();
        weatherRestTemplate.getForObject("url", OpenWeatherCityDTO.class, cityId);

        return null;
    }

    /**
     * Fetchs a five day forecast for a given city.
     *
     * @param cityId
     * @return
     */
    public Void getCityForecast(Long cityId) {

//        RestTemplate forecastRestTemplate = new RestTemplate();
//        forecastRestTemplate.getForObject("url", ForecastDTO.class, cityId);

        return null;
    }
}
