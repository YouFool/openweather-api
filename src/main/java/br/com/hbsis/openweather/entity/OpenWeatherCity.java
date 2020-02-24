package br.com.hbsis.openweather.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * Represents an OpenWeather city.
 */
@Document(collection = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherCity {

    /**
     * Unique identifier.
     */
    @Id
    private String id;

    /**
     * City unique identifier.
     */
    @Field("id")
    private Long cityId;

    /**
     * City name.
     */
    private String name;

    /**
     * City country code.
     */
    private String country;

    /**
     * Creates an OpenWeather city with name and country code.
     *
     * @param name    city name
     * @param country country code
     */
    public OpenWeatherCity(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
