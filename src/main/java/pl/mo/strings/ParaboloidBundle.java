package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class ParaboloidBundle extends ClassBundle {

    public ParaboloidBundle() {
        super("bundles.Paraboloid", Locale.getDefault());
    }

    @NotNull
    public String getTextOverwrittenMethods() {
        return bundle.getString("txtOverwrittenMethods");
    }

}
