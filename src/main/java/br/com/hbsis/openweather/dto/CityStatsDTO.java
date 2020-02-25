package br.com.hbsis.openweather.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO that represents temperature stats of a given city.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityStatsDTO {

    /**
     * Temperature.
     */
    @JsonAlias(value = "temp")
    private BigDecimal temperature;

    /**
     * Minimum temperature.
     */
    @JsonAlias(value = "temp_min")
    private BigDecimal minimumTemperature;

    /**
     * Maximum temperature.
     */
    @JsonAlias(value = "temp_max")
    private BigDecimal maximumTemperature;

    /**
     * Pressure.
     */
    private Long pressure;

    /**
     * Humidty.
     */
    private Long humidity;

}
