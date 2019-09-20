package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to communicate with OpenWeather API.
 */
@Service
public class OpenWeatherService {

    @Value("${openweather.api.key}")
    private String openWeatherApiKey;

}
