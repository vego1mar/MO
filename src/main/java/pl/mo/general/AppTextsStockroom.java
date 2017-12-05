package pl.mo.general;

import org.jetbrains.annotations.Contract;

public final class AppTextsStockroom {

    private static AppTextsStockroom singleton;
    private ParaboloidStrings paraboloidStrings;

    private AppTextsStockroom() {
        super();
        paraboloidStrings = new ParaboloidStrings();
    }

    public static synchronized AppTextsStockroom getInstance() {
        if (singleton == null) {
            singleton = new AppTextsStockroom();
        }

        return singleton;
    }

    @Contract(pure = true)
    public ParaboloidStrings getParaboloid() {
        return paraboloidStrings;
    }

    public final class ParaboloidStrings {

        private String msgOverwrittenMethod = "Method has no use due to insufficient parameter.";

        @Contract(pure = true)
        public String getMsgOverwrittenMethod() {
            return msgOverwrittenMethod;
        }

    }

}
