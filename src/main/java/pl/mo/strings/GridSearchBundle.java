package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class GridSearchBundle extends ClassBundle {

    public GridSearchBundle() {
        super("bundles.GridSearch", Locale.getDefault());
    }

    @NotNull
    public String getErrorNegativeArgumentDelta() {
        return bundle.getString("errNegativeArgumentDelta");
    }

}
