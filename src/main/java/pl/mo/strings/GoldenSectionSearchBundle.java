package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class GoldenSectionSearchBundle extends ClassBundle {

    public GoldenSectionSearchBundle() {
        super("bundles.GoldenSectionSearch", Locale.getDefault());
    }

    @NotNull
    public String getErrorIntervalArgumentsMismatch() {
        return bundle.getString("errIntervalArgumentsMismatch");
    }

}
