package br.com.hbsis.openweather.converter;

import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DTO converter for {@link OpenWeatherCity}.
 */
@UtilityClass
public class OpenWeatherCityConverter {

    /**
     * Groups forecasts by {@link LocalDate date} and puts them into a daily forecast.
     *
     * @param forecastDTO the {@link ForecastDTO} to have it's data grouped
     * @return a new DTO with daily forecasts
     */
    public ForecastDTO groupForecastsByDate(ForecastDTO forecastDTO) {
        Map<LocalDate, List<OpenWeatherCityDTO>> forecastsByDate = forecastDTO.getWeatherData()
                .stream()
                .collect(Collectors.groupingBy(OpenWeatherCityDTO::getDate));

        final List<OpenWeatherCityDTO> dailyForecasts = forecastsByDate.keySet().stream()
                .map(localDate -> forecastsByDate.get(localDate).get(0))
                .sorted(Comparator.comparing(OpenWeatherCityDTO::getDate))
                .collect(Collectors.toList());

        return new ForecastDTO(dailyForecasts);
    }

}
