package br.com.hbsis.openweather;

import br.com.hbsis.openweather.service.OpenWeatherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OpenweatherApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OpenweatherApplication.class, args);
        context.getBean(OpenWeatherService.class).createAllCities();
    }

}
