package br.com.hbsis.openweather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Represents a given city on the world.
 */
@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    /**
     * City unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_seq")
    @SequenceGenerator(name = "city_seq", sequenceName = "city_seq", allocationSize = 1)
    private Long id;

    /**
     * City name.
     */
    @Column(name = "name")
    @Size(max = 255)
    private String name;

    /**
     * City county code.
     */
    @Column(name = "country_code")
    @Size(max = 2)
    private String countryCode;

    /**
     * City Openweather identifier.
     */
    @Column(name = "open_weather_id")
    private String openWeatherId;

    /**
     * Constructs a city with name, country code and it's OpenWeather ID.
     *
     * @param name          city name
     * @param countryCode   country code
     * @param openWeatherId city OpenWeather ID
     */
    public City(@Size(max = 255) String name, @Size(max = 2) String countryCode, String openWeatherId) {
        this.name = name;
        this.countryCode = countryCode;
        this.openWeatherId = openWeatherId;
    }
}
