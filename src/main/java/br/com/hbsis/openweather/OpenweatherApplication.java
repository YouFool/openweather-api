package br.com.hbsis.openweather;

import br.com.hbsis.openweather.service.MongoImportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class OpenweatherApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(OpenweatherApplication.class, args);
		context.getBean(MongoImportService.class).importCitiesFile();
	}

}
