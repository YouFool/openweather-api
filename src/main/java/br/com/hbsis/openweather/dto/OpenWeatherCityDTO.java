package br.com.hbsis.openweather.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO that represents weather info and temperature stats.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherCityDTO {

    @JsonAlias(value = "weather")
    private List<CityWeatherDTO> cityWeathers;

    @JsonAlias(value = "main")
    private CityStatsDTO cityStats;

    /**
     * OpenWeather date comes in the following format: 2019-09-24 03:00:00.
     */
    @JsonAlias(value = "dt_txt")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String textDate;

    /**
     * The time of the given weather data.
     */
    private LocalDate date;

}
