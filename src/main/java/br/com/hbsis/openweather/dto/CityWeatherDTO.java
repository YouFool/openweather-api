package br.com.hbsis.openweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * City weather data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityWeatherDTO {

    /**
     * Weather identifier.
     */
    private Long id;

    /**
     * Main weather.
     */
    private String main;

    /**
     * Weather description.
     */
    private String description;

    /**
     * Weather icon.
     */
    private String icon;
}
