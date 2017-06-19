package com.fabway.smartquerybuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Template builder interface.
 * 
 * @param <B>
 *            the concrete builder type
 * @param <T>
 *            The template type
 */
public interface IBuilder<B, T extends ITemplate> {

    /**
     * Adds a condition in the builder context ({@link BuilderContext})
     * 
     * @param key
     *            the condition key
     * @param value
     * @param predicate
     * @return this builder
     * 
     * @see BuilderContext#given(String, Object, Predicate)
     */
    <O> B given(String key, O value, Predicate<O> predicate);

    /**
     * Alias to {@link IBuilder#given(String, Object, Predicate)}
     * 
     * @param key
     *            the condition key
     * @param value
     * @param predicate
     * @return this builder
     */
    <O> B g(String key, O value, Predicate<O> predicate);

    /**
     * Apply the method {@link ITemplate#addParameters(Object...)} .
     * 
     * @param value
     * @param conditions
     *            the precondition keys
     * @return this builder
     */
    B param(Object value, String... conditions);

    /**
     * Alias to {@link IBuilder#param(Object, String...)}
     * 
     * @param value
     * @param conditions
     * @return this builder
     */
    B p(Object value, String... conditions);

    /**
     * Shortcut for calling methods {@link #given(String, Object, Predicate)}
     * and {@link #param(Object, String...)}
     * 
     * <pre>
     *  .given("testAge", age, not(IsZero))
     *  .param(age, "testAge")
     * </pre>
     * 
     * You can only call
     * 
     * <pre>
     *  .givenParam("testAge", age, not(IsZero))
     * </pre>
     * 
     * @param key
     *            the condition key
     * @param value
     * @param predicate
     * @return this builder
     * @see BuilderContext#given(String, Object, Predicate)
     */
    <O> B givenParam(String key, O value, Predicate<O> predicate);

    /**
     * Alias to {@link IBuilder#givenParam(String, Object, Predicate)}
     * 
     * @param key
     *            the condition key
     * @param value
     * @param predicate
     * @return this builder
     */
    <O> B gp(String key, O value, Predicate<O> predicate);

    /**
     * Alias toe {@link ITemplate#get()}
     * 
     * @return the string
     */
    String get();

    /**
     * Alias to {@link ITemplate#build()}
     * 
     * @return the string
     */
    String build();

    /**
     * Alias to {@link ITemplate#getParameters()}
     * 
     * @return the list
     */
    List<Object> params();

    /**
     * Calls the {@link ITemplate#getParametersArray()}
     * 
     * @return the array
     */
    Object[] paramsAsArray();

    /**
     * Returns the current query instance.
     * 
     * @return the query instance
     */
    T getTemplate();

    /**
     * Returns the {@link BuilderContext} instance for this builder.
     * 
     * @return the instance.
     */
    BuilderContext getContext();

    /**
     * Creates a copy of this builder.
     * 
     * @return the new object
     */
    B copy();
}