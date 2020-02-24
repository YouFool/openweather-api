package br.com.hbsis.openweather.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO that represents a 5-day forecast.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDTO {

    /**
     * City weather data.
     */
    @JsonAlias(value = "list")
    private List<OpenWeatherCityDTO> weatherData;
}
