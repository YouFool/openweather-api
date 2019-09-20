package br.com.hbsis.openweather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents an OpenWeather city.
 */
@Document(collection = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherCity {

    @Id
    private String id;

    private String name;

    private String country;

    /**
     * Creates an OpenWeather city with name and country code.
     *
     * @param name city name
     * @param country country code
     */
    public OpenWeatherCity(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
