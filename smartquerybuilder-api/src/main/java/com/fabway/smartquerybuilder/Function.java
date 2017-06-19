package com.fabway.smartquerybuilder;

/**
 * Defines a function with a return value.
 * 
 * @param <I>
 *            the input value type
 * @param <O>
 *            the output value type
 */
public interface Function<I, O> {

    /**
     * Execute the expression.
     * 
     * @param input
     *            the input
     * @return the output value
     */
    O call(I input);

}