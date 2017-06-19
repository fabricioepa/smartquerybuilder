package com.fabway.smartquerybuilder.builders;

import java.util.function.Predicate;

import com.fabway.smartquerybuilder.BuilderContext;

/**
 * Builds a statement using preconditions. Example:
 * 
 * <pre>
 * {@code 
 * boolean admin = false;
 * Integer age = null;
 * 
 * StatementBuilder sb = new StatementBuilder()
 * .given("hasAge", age, and(not(IsZero), not(Null)))
 * .given("isAdmin", admin, True)
 * .param(age, "hasAge")
 * .put("DELETE FROM USER WHERE ID > 0")
 * .put(" AND AGE >= ? ", "hasAge")
 * .put(" AND IS_ADMIN = 1", "isAdmin");
 * 
 * //Now you can use the statement 
 * sb.get() //returns the mounted statement "DELETE FROM USER WHERE ... "
 * 
 * // returns the parameters in the position that should be used for the built statement
 * sb.getParamsList();
 * }
 * </pre>
 * 
 */
public class StatementBuilder {
	private StatementTemplate query = createTemplate();
	private BuilderContext builderContext = createContext();

	/**
	 * Default constructor.
	 */
	public StatementBuilder() {
		super();
	}

	/**
	 * Creates a copy from the source.
	 * 
	 * @param source
	 */
	public StatementBuilder(StatementBuilder source) {
		this.query = source.getQuery().clone();
	}

	/**
	 * Adds a condition in the {@link #builderContext}
	 * 
	 * @param key
	 *            the condition key
	 * @param value
	 * @param predicate
	 * @return this builder
	 * 
	 * @see BuilderContext#given(String, Object, Predicate)
	 */
	public <T> StatementBuilder given(String key, T value, Predicate<T> predicate) {
		builderContext.given(key, value, predicate);
		return this;
	}

	/**
	 * Alias to {@link StatementBuilder#given(String, Object, Predicate)}
	 * 
	 * @param key
	 *            the condition key
	 * @param value
	 * @param predicate
	 * @return this builder
	 */
	public <T> StatementBuilder g(String key, T value, Predicate<T> predicate) {
		this.given(key, value, predicate);
		return this;
	}

	/**
	 * Apply the method {@link StatementTemplate#addParams(Object...)} .
	 * 
	 * @param value
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public StatementBuilder param(Object value, String... conditions) {
		if (builderContext.results(conditions)) {
			getQuery().addParams(value);
		}
		return this;
	}

	/**
	 * Alias to {@link StatementBuilder#param(Object, String...)}
	 * 
	 * @param value
	 * @param conditions
	 * @return this builder
	 */
	public StatementBuilder p(Object value, String... conditions) {
		return this.param(value, conditions);
	}

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
	public <T> StatementBuilder givenParam(String key, T value, final Predicate<T> predicate) {
		this.getContext().given(key, value, predicate);
		param(value, key);
		return this;
	}

	/**
	 * Alias to {@link StatementBuilder#givenParam(String, Object, Predicate)}
	 * 
	 * @param key
	 *            the condition key
	 * @param value
	 * @param predicate
	 * @return this builder
	 */
	public <T> StatementBuilder gp(String key, T value, final Predicate<T> predicate) {
		return this.givenParam(key, value, predicate);
	}

	/**
	 * Apply the method {@link StatementTemplate#put(String)}.
	 * 
	 * @param text
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public StatementBuilder put(String text, String... conditions) {
		if (builderContext.results(conditions)) {
			getQuery().put(text);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public StatementBuilder clone() {
		return new StatementBuilder(this);
	}

	/**
	 * Returns the current query instance.
	 * 
	 * @return the query instance
	 */
	public StatementTemplate getQuery() {
		return query;
	}

	/**
	 * Returns the {@link BuilderContext} instance for this builder.
	 * 
	 * @return the instance.
	 */
	public BuilderContext getContext() {
		return builderContext;
	}

	protected StatementTemplate createTemplate() {
		return new StatementTemplate();
	}

	protected BuilderContext createContext() {
		return new BuilderContext();
	}

}