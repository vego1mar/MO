package pl.mo.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public strictfp class Vectors {

    private static final Logger log = Logger.getLogger(Vectors.class);

    private Vectors() {
        // This should be a utility class.
    }

    @Contract("null, _ -> null")
    public static <T extends Number> Double getNorm(List<T> vector, int level) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }

        Class<?> listType = vector.get(0).getClass();

        try {
            if (listType == Integer.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Integer.class), level).doubleValue());
            } else if (listType == Short.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Short.class), level).doubleValue());
            } else if (listType == Byte.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Byte.class), level).doubleValue());
            } else if (listType == Long.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Long.class), level).doubleValue());
            } else if (listType == Float.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Float.class), level).doubleValue());
            } else if (listType == Double.class) {
                return Math.sqrt(getSumOfPowers(cast(vector, Double.class), level));
            }
        } catch (NullPointerException ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    @Contract("null, _ -> null")
    public static <T extends Number> T getSumOfPowers(List<T> vector, int exponent) {
        if (vector == null || vector.isEmpty() || exponent <= 0) {
            return null;
        }

        Class<?> vectorType = vector.get(0).getClass();
        byte byteSum = (byte) 0;
        short shortSum = (short) 0;
        int intSum = 0;
        long longSum = 0L;
        float floatSum = 0.0f;
        double doubleSum = 0.0;

        try {
            if (vectorType == Byte.class) {
                for (T element : vector) {
                    byteSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Byte.valueOf(byteSum);
            } else if (vectorType == Short.class) {
                for (T element : vector) {
                    shortSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Short.valueOf(shortSum);
            } else if (vectorType == Integer.class) {
                for (T element : vector) {
                    intSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Integer.valueOf(intSum);
            } else if (vectorType == Long.class) {
                for (T element : vector) {
                    longSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Long.valueOf(longSum);
            } else if (vectorType == Float.class) {
                for (T element : vector) {
                    floatSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Float.valueOf(floatSum);
            } else if (vectorType == Double.class) {
                for (T element : vector) {
                    doubleSum += Power.getFast(element.doubleValue(), exponent);
                }

                return (T) Double.valueOf(doubleSum);
            }
        } catch (ClassCastException | NumberFormatException ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    public static <T extends Number, E extends Number> List<E> cast(@NotNull List<T> vector, Class<E> tClass) {
        List<E> list = new ArrayList<>();

        try {
            for (T element : vector) {
                if (tClass == Integer.class) {
                    list.add((E) Integer.valueOf(element.toString()));
                } else if (tClass == Short.class) {
                    list.add((E) Short.valueOf(element.toString()));
                } else if (tClass == Byte.class) {
                    list.add((E) Byte.valueOf(element.toString()));
                } else if (tClass == Long.class) {
                    list.add((E) Long.valueOf(element.toString()));
                } else if (tClass == Float.class) {
                    list.add((E) Float.valueOf(element.toString()));
                } else if (tClass == Double.class) {
                    list.add((E) Double.valueOf(element.toString()));
                }
            }
        } catch (ClassCastException | NullPointerException | NumberFormatException ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }

        return list;
    }

}
