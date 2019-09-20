package br.com.hbsis.openweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Represents a city DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String countryCode;
}
