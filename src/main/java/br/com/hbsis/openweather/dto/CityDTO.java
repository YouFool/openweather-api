package br.com.hbsis.openweather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO that represents a city.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 2)
    private String countryCode;
}
