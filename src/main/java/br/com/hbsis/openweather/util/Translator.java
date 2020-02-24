package br.com.hbsis.openweather.util;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Translator for application messages.
 */
public class Translator {

    private Translator() {
        // utility class
    }

    /**
     * Translates a bundle message based on it's key. Formats message if varargs are present.
     *
     * @param key the {@link Messages} translation key
     * @param params variable arguments
     * @return the formatted message, or an empty string
     */
    public static String translateMessage(Messages key, Object... params) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages_en_US");
        String message = resourceBundle.getString(key.getMessageCode());

        if (StringUtils.hasText(message) && params.length != 0) {
            try {
                return MessageFormat.format(message, params);
            } catch (Exception e) {
                // fails silently
            }
        }

        return message;
    }
}
