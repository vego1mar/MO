package pl.mo.strings;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class MainWindowBundle extends ClassBundle {

    public MainWindowBundle() {
        super("bundles.MainWindow", Locale.getDefault());
    }

    @NotNull
    public String getAppTitle() {
        return bundle.getString("appTitle");
    }

    @NotNull
    public String getFXMLResourcePath() {
        return bundle.getString("fxmlResourcePath");
    }

    @NotNull
    public String getTextImproperValue() {
        return bundle.getString("txtImproperValue");
    }

    @NotNull
    public String getAbbreviationNotApplicable() {
        return bundle.getString("abbreviationNotApplicable");
    }

    @NotNull
    public String getTextValueNotFound() {
        return bundle.getString("txtValueNotFound");
    }

    @NotNull
    public String getLineChartsTitle() {
        return bundle.getString("lineChartsTitle");
    }

    @NotNull
    public String getDependencyChartTitle() {
        return bundle.getString("dependencyChartTitle");
    }

    @NotNull
    public String getErrorIntervalArgumentsMismatch() {
        return bundle.getString("errIntervalArgumentsMismatch");
    }

    @NotNull
    public String getErrorNegativeIntervalDivisionsNo() {
        return bundle.getString("errNegativeIntervalDivisionsNo");
    }

}
