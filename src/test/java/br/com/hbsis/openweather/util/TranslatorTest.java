package br.com.hbsis.openweather.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Translator} utility class.
 */
public class TranslatorTest {

    @Test
    public void should_translate_message() {
        final String cityName = "MyCity123";
        final String result = Translator.translateMessage(Messages.ERROR_CITY_WITH_NAME_NOT_FOUND, cityName);
        final String expected = "City with name: ".concat(cityName).concat(" not found!");
        assertThat(result).isEqualTo(expected);
    }

}