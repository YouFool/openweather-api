package br.com.hbsis.openweather.repository;

import br.com.hbsis.openweather.entity.OpenWeatherCity;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherCityRepositoryTest {

    @Autowired
    private OpenWeatherCityRepository openWeatherCityRepository;

    @After
    public void tearDown() {
        this.openWeatherCityRepository.deleteAll();
    }

    @Test
    public void should_find_city_by_name_and_country_code() {
        String blumenauCityName = "Blumenau";
        String brazilCountryCode = "BR";

        OpenWeatherCity openWeatherCity1 = new OpenWeatherCity(blumenauCityName, brazilCountryCode);
        OpenWeatherCity openWeatherCity2 = new OpenWeatherCity("Indaial", brazilCountryCode);
        this.openWeatherCityRepository.save(openWeatherCity1);
        this.openWeatherCityRepository.save(openWeatherCity2);

        final List<OpenWeatherCity> citiesFound = this.openWeatherCityRepository.findByNameAndCountry(blumenauCityName, brazilCountryCode);

        assertThat(citiesFound.get(0).getName()).isEqualTo(blumenauCityName);
    }

}
