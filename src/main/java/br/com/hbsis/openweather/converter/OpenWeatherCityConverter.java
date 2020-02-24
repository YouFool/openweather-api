package br.com.hbsis.openweather.converter;

import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DTO converter for {@link OpenWeatherCity}.
 */
@UtilityClass
public class OpenWeatherCityConverter {

    /**
     * Groups 3-hour forecasts by {@link LocalDate} date and inputs them into a daily forecast.
     *
     * @param forecastDTO the {@link ForecastDTO} to have it's data grouped
     * @return a new DTO with daily forecasts
     */
    public ForecastDTO groupForecastsByDate(ForecastDTO forecastDTO) {

        // groups forecasts by date
        Map<LocalDateTime, List<OpenWeatherCityDTO>> forecastsByDateMap = forecastDTO.getWeatherData()
                .stream()
                .collect(Collectors.groupingBy(OpenWeatherCityDTO::getDate));

        // iterates the grouped map and puts daily forecasts in the temporary array
        List<OpenWeatherCityDTO> dailyForecasts = new ArrayList<>();
        forecastsByDateMap.forEach((date, openWeatherCityDTOS) ->
                dailyForecasts.add(forecastsByDateMap.get(date).get(0)));
        Collections.reverse(dailyForecasts);

        return new ForecastDTO(dailyForecasts);
    }

}
