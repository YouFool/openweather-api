package br.com.hbsis.openweather.configuration;

import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.flapdoodle.embed.mongo.MongoImportExecutable;
import de.flapdoodle.embed.mongo.MongoImportStarter;
import de.flapdoodle.embed.mongo.config.IMongoImportConfig;
import de.flapdoodle.embed.mongo.config.MongoImportConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class CityConfiguration {

    /**
     * Reads a file containing all OpenWeather available cities.
     *
     * @return
     */
    public List<OpenWeatherCity> readCities() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<OpenWeatherCity>> mapType = new TypeReference<List<OpenWeatherCity>>() {
        };
        InputStream is = TypeReference.class.getResourceAsStream("/static/city.list.json");
        try {
            List<OpenWeatherCity> cities = mapper.readValue(is, mapType);
            return cities;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }


}
