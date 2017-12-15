package pl.mo.strings;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class ClassBundle {

    protected ResourceBundle bundle;

    public ClassBundle(String baseName, Locale locale) {
        bundle = ResourceBundle.getBundle(baseName, locale);
    }

}
