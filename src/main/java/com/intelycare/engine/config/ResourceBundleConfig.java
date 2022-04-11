package com.intelycare.engine.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleConfig {

    @Getter
    @Setter
    private static Locale locale;

    static {
        locale = Locale.getDefault();
    }

    public static String getWord(String key) {

        ResourceBundle words
                = ResourceBundle.getBundle("i18n/words", locale);

        return words.getString(key);

    }
}