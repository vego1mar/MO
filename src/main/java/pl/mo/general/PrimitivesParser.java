package pl.mo.general;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;

public class PrimitivesParser {

    private static final Logger log = Logger.getLogger(PrimitivesParser.class);
    private static String errorMessage = null;

    private PrimitivesParser() {
        // This should be a utility class.
    }

    @Contract(pure = true)
    public static String getErrorMessage() {
        return errorMessage;
    }

    public static Double getDouble(String text) {
        Double result = null;
        errorMessage = null;

        try {
            result = Double.parseDouble(text);
        } catch (NullPointerException | NumberFormatException ex) {
            errorMessage = ex.getMessage();
            log.error(ex.getMessage());
        }

        return result;
    }

    public static Integer getInteger(String text) {
        Integer result = null;
        errorMessage = null;

        try {
            result = Integer.parseInt(text);
        } catch (NullPointerException | NumberFormatException ex) {
            errorMessage = ex.getMessage();
            log.error(ex.getMessage());
        }

        return result;
    }

}
