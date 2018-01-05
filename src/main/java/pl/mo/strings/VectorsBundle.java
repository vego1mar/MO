package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class VectorsBundle extends ClassBundle {

    public VectorsBundle() {
        super("bundles.Vectors", Locale.getDefault());
    }

    @NotNull
    public String getWarningVectorsSizesNotEqual() {
        return bundle.getString("warningVectorsSizesNotEqual");
    }

}
