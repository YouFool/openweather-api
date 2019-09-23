package br.com.hbsis.openweather.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
