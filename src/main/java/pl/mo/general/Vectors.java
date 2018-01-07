package pl.mo.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.mo.strings.VectorsBundle;

public final strictfp class Vectors {

    private static final Logger log = Logger.getLogger(Vectors.class);
    private static final VectorsBundle bundle = new VectorsBundle();

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

    @Contract("null -> !null")
    public static <T extends Number> List<T> negate(List<T> vector) {
        if (vector == null || vector.isEmpty()) {
            return Collections.emptyList();
        }

        Class<?> vectorType = vector.get(0).getClass();
        List<T> negatedVector = new ArrayList<>();

        try {
            for (T element : vector) {
                if (vectorType == Byte.class) {
                    byte value = (byte) element;
                    value *= -1;
                    negatedVector.add((T) Byte.valueOf(value));
                } else if (vectorType == Short.class) {
                    short value = (short) element;
                    value *= -1;
                    negatedVector.add((T) Short.valueOf(value));
                } else if (vectorType == Integer.class) {
                    negatedVector.add((T) Integer.valueOf(-((int) element)));
                } else if (vectorType == Long.class) {
                    negatedVector.add((T) Long.valueOf(-((long) element)));
                } else if (vectorType == Float.class) {
                    negatedVector.add((T) Float.valueOf(-((float) element)));
                } else if (vectorType == Double.class) {
                    negatedVector.add((T) Double.valueOf(-((double) element)));
                }
            }
        } catch (ClassCastException | NullPointerException ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }

        return negatedVector;
    }

    @Contract("null, _ -> !null")
    public static <T extends Number> List<T> multiplyByScalar(List<T> vector, T scalar) {
        if (vector == null || vector.isEmpty()) {
            return Collections.emptyList();
        }

        Class<?> vectorType = vector.get(0).getClass();
        List<T> multipliedVector = new ArrayList<>();

        try {
            for (T element : vector) {
                if (vectorType == Byte.class) {
                    byte value = (byte) element;
                    value *= (byte) scalar;
                    multipliedVector.add((T) Byte.valueOf(value));
                } else if (vectorType == Short.class) {
                    short value = (short) element;
                    value *= (short) scalar;
                    multipliedVector.add((T) Short.valueOf(value));
                } else if (vectorType == Integer.class) {
                    multipliedVector.add((T) Integer.valueOf((int) element * (int) scalar));
                } else if (vectorType == Long.class) {
                    multipliedVector.add((T) Long.valueOf((long) element * (long) scalar));
                } else if (vectorType == Float.class) {
                    multipliedVector.add((T) Float.valueOf((float) element * (float) scalar));
                } else if (vectorType == Double.class) {
                    multipliedVector.add((T) Double.valueOf((double) element * (double) scalar));
                }
            }
        } catch (ClassCastException | NullPointerException ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }

        return multipliedVector;
    }

    @Contract("null, _ -> !null; !null, null -> !null")
    public static <T extends Number> List<T> add(List<T> vector1, List<T> vector2) {
        if (vector1 == null || vector2 == null || vector1.isEmpty() || vector2.isEmpty()) {
            return Collections.emptyList();
        }

        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException(bundle.getWarningVectorsSizesNotEqual());
        }

        Class<?> vectorTypes = vector1.get(0).getClass();
        List<T> additionVector = new ArrayList<>();

        try {
            for (int i = 0; i < vector1.size(); i++) {
                if (vectorTypes == Byte.class) {
                    byte value = (byte) vector1.get(i);
                    value += (byte) vector2.get(i);
                    additionVector.add((T) Byte.valueOf(value));
                } else if (vectorTypes == Short.class) {
                    short value = (short) vector1.get(i);
                    value += (short) vector2.get(i);
                    additionVector.add((T) Short.valueOf(value));
                } else if (vectorTypes == Integer.class) {
                    additionVector.add((T) Integer.valueOf((int) vector1.get(i) + (int) vector2.get(i)));
                } else if (vectorTypes == Long.class) {
                    additionVector.add((T) Long.valueOf((long) vector1.get(i) + (long) vector2.get(i)));
                } else if (vectorTypes == Float.class) {
                    additionVector.add((T) Float.valueOf((float) vector1.get(i) + (float) vector2.get(i)));
                } else if (vectorTypes == Double.class) {
                    additionVector.add((T) Double.valueOf((double) vector1.get(i) + (double) vector2.get(i)));
                }
            }
        } catch (ClassCastException | NullPointerException | NumberFormatException ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }

        return additionVector;
    }

    @Nullable
    public static <T extends Number, E extends Number> E cast(T value, Class<E> targetType) {
        try {
            if (targetType == Byte.class) {
                return (E) Byte.valueOf(value.toString());
            } else if (targetType == Short.class) {
                return (E) Short.valueOf(value.toString());
            } else if (targetType == Integer.class) {
                return (E) Integer.valueOf(value.toString());
            } else if (targetType == Long.class) {
                return (E) Long.valueOf(value.toString());
            } else if (targetType == Float.class) {
                return (E) Float.valueOf(value.toString());
            } else if (targetType == Double.class) {
                return (E) Double.valueOf(value.toString());
            }
        } catch (ClassCastException | NullPointerException | NumberFormatException ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    @Contract("null, _ -> null")
    public static <T extends Number> T multiplyAsMatrix(List<T> vector1, List<T> vector2) {
        if (vector1 == null || vector1.isEmpty() || vector2 == null || vector2.isEmpty()) {
            return null;
        }

        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException(bundle.getWarningVectorsSizesNotEqual());
        }

        Class<?> vectorTypes = vector2.get(0).getClass();
        byte byteSum = (byte) 0;
        short shortSum = (short) 0;
        int intSum = 0;
        long longSum = 0L;
        float floatSum = 0.0f;
        double doubleSum = 0.0;

        try {
            for (int i = 0; i < vector2.size(); i++) {
                if (vectorTypes == Byte.class) {
                    byte value = (byte) vector1.get(i);
                    value *= (byte) vector2.get(i);
                    byteSum += value;
                } else if (vectorTypes == Short.class) {
                    short value = (short) vector1.get(i);
                    value *= (short) vector2.get(i);
                    shortSum += value;
                } else if (vectorTypes == Integer.class) {
                    intSum += (int) vector1.get(i) * (int) vector2.get(i);
                } else if (vectorTypes == Long.class) {
                    longSum += (long) vector1.get(i) * (long) vector2.get(i);
                } else if (vectorTypes == Float.class) {
                    floatSum += (float) vector1.get(i) * (float) vector2.get(i);
                } else if (vectorTypes == Double.class) {
                    doubleSum += (double) vector1.get(i) * (double) vector2.get(i);
                }
            }

            if (vectorTypes == Byte.class) {
                return (T) Byte.valueOf(byteSum);
            } else if (vectorTypes == Short.class) {
                return (T) Short.valueOf(shortSum);
            } else if (vectorTypes == Integer.class) {
                return (T) Integer.valueOf(intSum);
            } else if (vectorTypes == Long.class) {
                return (T) Long.valueOf(longSum);
            } else if (vectorTypes == Float.class) {
                return (T) Float.valueOf(floatSum);
            } else if (vectorTypes == Double.class) {
                return (T) Double.valueOf(doubleSum);
            }
        } catch (ClassCastException | NullPointerException | NumberFormatException ex) {
            log.error(ex.getMessage());
            return null;
        }

        return null;
    }

    @Contract("null, null -> true; null, !null -> false; !null, null -> false")
    public static <T extends Number> boolean isAs(List<T> vector1, List<T> vector2) {
        if (vector1 == null && vector2 == null) {
            return true;
        }

        if (vector1 == null || vector2 == null) {
            return false;
        }

        if (vector1.size() != vector2.size()) {
            return false;
        }

        Class<?> vectorTypes = vector1.get(0).getClass();
        int comparingResult = 0;

        try {
            for (int i = 0; i < vector1.size(); i++) {
                if (vectorTypes == Byte.class) {
                    comparingResult = Byte.compare(vector1.get(i).byteValue(), vector2.get(i).byteValue());
                } else if (vectorTypes == Short.class) {
                    comparingResult = Short.compare(vector1.get(i).shortValue(), vector2.get(i).shortValue());
                } else if (vectorTypes == Integer.class) {
                    comparingResult = Integer.compare(vector1.get(i).intValue(), vector2.get(i).intValue());
                } else if (vectorTypes == Long.class) {
                    comparingResult = Long.compare(vector1.get(i).longValue(), vector2.get(i).longValue());
                } else if (vectorTypes == Float.class) {
                    comparingResult = Float.compare(vector1.get(i).floatValue(), vector2.get(i).floatValue());
                } else if (vectorTypes == Double.class) {
                    comparingResult = Double.compare(vector1.get(i).doubleValue(), vector2.get(i).doubleValue());
                }

                if (comparingResult != 0) {
                    return false;
                }
            }
        } catch (ClassCastException | NullPointerException ex) {
            log.error(ex.getMessage());
            return false;
        }

        return true;
    }

}
