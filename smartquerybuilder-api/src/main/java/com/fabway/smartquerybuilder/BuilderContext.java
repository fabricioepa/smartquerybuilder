package com.fabway.smartquerybuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This class evaluates and stores the results of the preconditions for the
 * constructors.
 */
public class BuilderContext {

    private Map<String, Boolean> results = new HashMap<String, Boolean>();

    public BuilderContext() {
    }

    /**
     * Copy from given context.
     * 
     * @param source
     */
    public BuilderContext(BuilderContext source) {
        this();
        this.results = new HashMap<String, Boolean>(source.results);
    }

    /**
     * Adds the precondition using the unique key and stores the evaluation
     * result from the given value and the predicate. The stored results can
     * also be read later using {@link #result(String)} method.
     * 
     * @param key
     *            the condition key
     * @param value
     *            to be evaluated
     * @param predicate
     * @return the evaluation result
     */
    public <T> boolean given(String key, T value, Predicate<T> predicate) {
        boolean result = predicate.test(value);
        storeResult(key, result);
        return result;
    }

    /**
     * Returns true if all evaluation results are true.
     * 
     * @param keys
     *            the precondition keys
     * @return the evaluated value
     */
    public boolean results(String... keys) {
        return Stream.of(keys).filter(k -> !result(k)).count() == 0;
    }

    /**
     * Returns the evaluation result for this condition.
     * 
     * @param key
     *            the condition key
     * @return the test result stored.
     */
    public boolean result(String key) {
        Boolean val = results.get(key);
        return val == null ? false : val;
    }

    private void storeResult(String key, boolean result) {
        results.put(key, result);
    }

    @Override
    public BuilderContext clone() {
        return new BuilderContext(this);
    }
}