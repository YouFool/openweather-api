package br.com.hbsis.openweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityStatsDTO {

    private BigDecimal temp;
    private Long pressure;
    private Long humidity;
    private BigDecimal temp_min;
    private BigDecimal temp_max;
}
