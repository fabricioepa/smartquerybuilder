package com.fabway.smartquerybuilder;

/**
 * Represents a conditional check for an object value. This class exists for
 * backward compatibility with previous versions of java 8 with no java.util.Predicate support.
 * 
 */
public interface Condition {

	/**
	 * Returns true if the condition is valid.
	 * 
	 * @param value
	 *            to be evaluated
	 * @return true if valid
	 */
	boolean evaluate(Object value);

}