package br.com.hbsis.openweather.configuration;

import br.com.hbsis.openweather.entity.OpenWeatherCity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CityConfiguration} bean.
 */
public class CityConfigurationTest {

    @InjectMocks
    private CityConfiguration cityConfiguration;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_read_cities_json_file() {
        List<OpenWeatherCity> openWeatherCities = cityConfiguration.readCities();
        assertThat(openWeatherCities).isNotEmpty();
    }
}