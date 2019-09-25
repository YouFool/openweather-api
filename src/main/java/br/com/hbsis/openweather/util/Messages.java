package br.com.hbsis.openweather.util;

import lombok.Getter;

/**
 * Represents application messages.
 */
@Getter
public enum Messages {

    ERROR_FAILED_TO_FETCH_API_DATA("error.api.data"),
    ERROR_CITY_WITH_ID_NOT_FOUND("error.city.notfound.id"),
    ERROR_CITY_WITH_NAME_NOT_FOUND("error.city.notfound.name");

    String messageCode;

    private Messages(String messageCode) {
        this.messageCode = messageCode;
    }

}
