package pl.mo.general;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PrimitivesHelper {

    private static final Logger log = Logger.getLogger(PrimitivesHelper.class);
    private static String errorMessage = null;

    private PrimitivesHelper() {
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

    @Nullable
    public static <T> T castInto(Object object, @NotNull Class<T> tClass) {
        T castedObject;
        errorMessage = null;

        try {
            castedObject = tClass.cast(object);
        } catch (ClassCastException | NumberFormatException ex) {
            errorMessage = ex.getMessage();
            log.error(ex.getMessage());
            return null;
        }

        return castedObject;
    }

}
