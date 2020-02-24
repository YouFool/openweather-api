package br.com.hbsis.openweather.service;

import br.com.hbsis.openweather.entity.City;
import br.com.hbsis.openweather.entity.OpenWeatherCity;
import br.com.hbsis.openweather.repository.CityRepository;
import br.com.hbsis.openweather.repository.OpenWeatherCityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CityService}.
 */
public class CityServiceTest {

    @Mock
    private CityRepository mockCityRepository;

    @Mock
    private OpenWeatherCityRepository mockOpenWeatherCityRepository;

    @InjectMocks
    private CityService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_city() {
        final String cityName = "Auckland";
        final String countryCode = "NZ";
        OpenWeatherCity openWeatherCity = new OpenWeatherCity("333", 1L, cityName, countryCode);

        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        when(mockOpenWeatherCityRepository.findByNameAndCountry(cityName, countryCode))
                .thenReturn(Collections.singletonList(openWeatherCity));

        service.createCity(cityName, countryCode);
        verify(mockCityRepository).save(cityArgumentCaptor.capture());
        final City result = cityArgumentCaptor.getValue();

        assertThat(result.getName()).isEqualTo(cityName);
        assertThat(result.getCountryCode()).isEqualTo(countryCode);
        assertThat(result.getOpenWeatherId()).isEqualTo(Long.parseLong(openWeatherCity.getId()));
    }

    @Test
    public void should_throw_exception_when_city_does_not_exists() {
        final String cityName = "Auckland";
        final String countryCode = "NZ";
        OpenWeatherCity openWeatherCity = new OpenWeatherCity("333", 1L, cityName, countryCode);

        try {
            service.createCity(cityName, countryCode);
            failBecauseExceptionWasNotThrown(ResponseStatusException.class);
        } catch (ResponseStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(e.getReason()).isEqualTo("City with name: ".concat(cityName).concat(" not found!"));
        }
    }
}