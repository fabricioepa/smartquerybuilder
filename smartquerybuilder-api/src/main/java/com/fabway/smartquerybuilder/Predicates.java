package com.fabway.smartquerybuilder;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Predicates ready for use.
 * 
 * @see java.util.function.Predicate
 */
public final class Predicates {

    protected Predicates() {
    }

    public static Predicate<Object> True = val -> Conditions.True.evaluate(val);

    public static Predicate<Object> False = val -> Conditions.False.evaluate(val);

    public static Predicate<Object> Null = val -> Conditions.Null.evaluate(val);

    public static Predicate<Object> NotNull = val -> Conditions.NotNull.evaluate(val);

    public static Predicate<Object> Empty = val -> Conditions.Empty.evaluate(val);

    public static Predicate<Object> IsZero = val -> Conditions.IsZero.evaluate(val);

    /**
     * Encapsulate all predicates using the AND operation.
     * 
     * @param predicates
     * @return the new predicate
     */
    @SafeVarargs
    public static final <T> Predicate<T> and(final Predicate<T>... predicates) {
        if (predicates == null || predicates.length == 0) {
            return null;
        }
        return (T val) -> Stream.of(predicates).allMatch(p -> p.test(val));
    }

    /**
     * Encapsulate all predicates using the OR operation.
     * 
     * @param predicates
     * @return the new predicate
     */
    @SafeVarargs
    public static <T> Predicate<T> or(final Predicate<T>... predicates) {
        if (predicates == null || predicates.length == 0) {
            return null;
        }
        return (T val) -> Stream.of(predicates).anyMatch(p -> p.test(val));
    }

    /**
     * Negates the predicate using the NOT operator.
     * 
     * @param p
     *            the predicate
     * @return the new predicate
     */
    public static <T> Predicate<T> not(final Predicate<T> p) {
        if (p == null) {
            return null;
        }
        return p.negate();
    }

}