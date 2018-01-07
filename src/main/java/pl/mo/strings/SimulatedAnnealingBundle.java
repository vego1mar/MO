package pl.mo.strings;

import java.util.Locale;

public class SimulatedAnnealingBundle extends ClassBundle {

    public SimulatedAnnealingBundle() {
        super("bundles.SimulatedAnnealing", Locale.getDefault());
    }

    public String getErrorPointIsNot2D() {
        return bundle.getString("errorPointIsNot2D");
    }

    public String getErrorIntervalInvalid() {
        return bundle.getString("errorIntervalInvalid");
    }

    public String getErrorAnnealingThreshold() {
        return bundle.getString("errorAnnealingThreshold");
    }

    public String getErrorTemperatureCoolingFactor() {
        return bundle.getString("errorTemperatureCoolingFactor");
    }

}
