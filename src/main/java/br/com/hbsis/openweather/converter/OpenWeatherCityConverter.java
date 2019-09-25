package br.com.hbsis.openweather.converter;

import br.com.hbsis.openweather.dto.ForecastDTO;
import br.com.hbsis.openweather.dto.OpenWeatherCityDTO;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DTO converter for {@link OpenWeatherCity}.
 */
@Component
public class OpenWeatherCityConverter {

    private DateTimeFormatter openWeatherDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Groups 3-hour forecasts by {@link LocalDate} date and inputs them into a daily forecast.
     *
     * @param forecastDTO the {@link ForecastDTO} to have it's data grouped
     * @return a new DTO with daily forecasts
     */
    public ForecastDTO groupForecastsByDate(ForecastDTO forecastDTO) {
        List<OpenWeatherCityDTO> weatherData = forecastDTO.getWeatherData();
        this.formatData(weatherData);

        // groups forecasts by date
        Map<LocalDate, List<OpenWeatherCityDTO>> forecastsByDateMap = weatherData
                .stream()
                .collect(Collectors.groupingBy(OpenWeatherCityDTO::getDate));

        // iterates the grouped map and puts daily forecasts in the temporary array
        List<OpenWeatherCityDTO> dailyForecasts = new ArrayList<>();
        forecastsByDateMap.forEach((date, openWeatherCityDTOS) ->
                dailyForecasts.add(forecastsByDateMap.get(date).get(0)));
        Collections.reverse(dailyForecasts);

        return new ForecastDTO(dailyForecasts);
    }

    /**
     * Formats OpenWeather String date to {@link LocalDateTime}.
     *
     * @param weatherData list of weather DTOs
     */
    private void formatData(List<OpenWeatherCityDTO> weatherData) {
        weatherData.forEach(openWeatherCityDTO -> {
            final LocalDateTime localDateTime = LocalDateTime.parse(openWeatherCityDTO.getTextDate(), openWeatherDateFormat);
            openWeatherCityDTO.setDate(localDateTime.toLocalDate());
        });
    }
}
