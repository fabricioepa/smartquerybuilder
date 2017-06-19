package com.fabway.smartquerybuilder;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * Utility class for assert object values.
 * 
 */
public final class Assertions {

    private Assertions() {
    }

    /**
     * Asserts that a value is not null and matches {@link Boolean#TRUE}.
     * 
     * @param val
     * @return true if assertion succeeds
     * 
     * @see Boolean#parseBoolean(String)
     */
    public static boolean isTrue(Object val) {
        return val != null && Boolean.parseBoolean(val.toString());
    }

    /**
     * Asserts that a value is not null and matches {@link Boolean#FALSE}.
     * 
     * @param val
     * @return true if assertion succeeds
     * 
     * @see Boolean#parseBoolean(String)
     */
    public static boolean isFalse(Object val) {
        return val != null && !Boolean.parseBoolean(val.toString());
    }

    /**
     * Asserts that a value is null.
     * 
     * @param val
     * @return true if assertion succeeds
     */
    public static boolean isNull(Object val) {
        return val == null;
    }

    /**
     * Asserts that a value is not null.
     * 
     * @param val
     * @return true if assertion succeeds
     */
    public static boolean notNull(Object val) {
        return val != null;
    }

    /**
     * Asserts that a type and a value are not null and the type is assignable
     * from the value.
     * 
     * @param val
     * @param type
     * @return true if assertion succeeds
     */
    public static boolean isType(Object val, Class<?> type) {
        return type != null && val != null && type.isAssignableFrom(val.getClass());
    }

    /**
     * Asserts that a value is not null and matches the number zero.
     * 
     * @param val
     *            should be a parsable number value.
     * @return true if assertion succeeds
     */
    public static boolean isZero(Object val) {
        try {
            if (val == null) {
                return false;
            }

            if (isType(val, Number.class)) {
                return ((Number) val).doubleValue() == 0.0d;
            }

            return Double.parseDouble(val.toString()) == 0.0d;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Asserts that a value is not null and it is empty based on type. Supported
     * types:
     * <ul>
     * <li>{@link String} - Empty if it is a blank string</li>
     * <li>{@link Array} - Empty if length is zero</li>
     * <li>{@link Collection} - empty if size is zero</li>
     * </ul>
     * 
     * @param val
     * @return true if assertion succeeds
     * @throws IllegalArgumentException
     *             if the object type is not supported
     */
    public static boolean isEmpty(Object val) {
        if (val == null) {
            return true;
        }

        Class<? extends Object> valueClass = val.getClass();
        if (Collection.class.isAssignableFrom(valueClass)) {
            return ((Collection<?>) val).isEmpty();
        } else if (String.class.isAssignableFrom(valueClass)) {
            return val.toString().trim().length() == 0;
        } else if (valueClass.isArray()) {
            return Array.getLength(val) == 0;
        }
        throw new IllegalArgumentException("The type is not supported by the condition");
    }
}