package br.com.hbsis.openweather.configuration;

import br.com.hbsis.openweather.entity.OpenWeatherCity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class CityConfiguration {

    /**
     * Reads a JSON file containing all OpenWeather available cities.
     *
     * @return a {@link List} of {@link OpenWeatherCity} cities
     * @throws ResponseStatusException if any I/O error occurs
     */
    public List<OpenWeatherCity> readCities() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<OpenWeatherCity>> mapType = new TypeReference<List<OpenWeatherCity>>() {
        };

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/static/city.list.json")) {
            List<OpenWeatherCity> cities = mapper.readValue(inputStream, mapType);
            return cities;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }


}
