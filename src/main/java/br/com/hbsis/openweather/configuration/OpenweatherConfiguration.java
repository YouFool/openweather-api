package br.com.hbsis.openweather.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration responsible to map Openweather integration properties.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "openweather")
public class OpenweatherConfiguration {

    /**
     * API configuration properties.
     */
    private Api api;

    /**
     * API configuration properties.
     */
    @Getter
    @Setter
    @SuppressWarnings("WeakerAccess")
    public static class Api {

        /**
         * API Base URL.
         */
        private String baseUrl;

        /**
         * API Key.
         */
        private String key;

    }


}





