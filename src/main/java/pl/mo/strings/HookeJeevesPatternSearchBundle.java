package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class HookeJeevesPatternSearchBundle extends ClassBundle {

    public HookeJeevesPatternSearchBundle() {
        super("bundles.HookeJeevesPatternSearch", Locale.getDefault());
    }

    @NotNull
    public String getWarningArgumentVectorsDoesNotMatch() {
        return bundle.getString("warningArgumentVectorsDoesNotMatch");
    }

    @NotNull
    public String getWarningVectorIsNot2Dimensional() {
        return bundle.getString("warningVectorIsNot2Dimensional");
    }

}
