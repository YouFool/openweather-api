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

/**
 * Bean responsible for {@link OpenWeatherCity} configurations.
 */
@Configuration
public class CityConfiguration {

    /**
     * Reads a JSON file containing all OpenWeather cities.
     *
     * @return a {@link List} of {@link OpenWeatherCity} cities
     * @throws ResponseStatusException if any I/O error occurs
     */
    public List<OpenWeatherCity> readCities() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<OpenWeatherCity>> mapType = new TypeReference<List<OpenWeatherCity>>() {
        };

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/static/city.list.json")) {
            return mapper.readValue(inputStream, mapType);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }


}
