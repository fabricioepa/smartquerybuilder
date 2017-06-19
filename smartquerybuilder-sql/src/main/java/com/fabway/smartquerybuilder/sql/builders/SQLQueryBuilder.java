package com.fabway.smartquerybuilder.sql.builders;

import java.util.function.Predicate;

import com.fabway.smartquerybuilder.BuilderContext;

/**
 * Builder for creating and configuring the {@link SQLQueryTemplate} object.
 * Usage:
 * 
 * <code>
 * <pre>
 * Long roleId = null;
 * SLQueryBuilder query = new SQLQueryBuilder()
 * 		.test("t1", roleId, not(IsZero), NotNull)
 * 		.param(roleId, "t1")
 * 		.from("from Users u")
 * 		.from(" inner join Role role on role.id = u.role_id ", "t1")
 * 		.where(" where u.id > 0")
 * 		.where(" and role.id = ? ", "t1");
 * 		
 * String sqlCount = query.clone().select("count(u.id)").get();
 * String sqlSelect = query.clone().select("select u.*").get();
 * </pre>
 * </code>
 * 
 */
public class SQLQueryBuilder {
	private SQLQueryTemplate query = new SQLQueryTemplate();
	private BuilderContext builderContext = new BuilderContext();

	/**
	 * Default constructor.
	 */
	public SQLQueryBuilder() {
		super();
		build();
	}

	/**
	 * Creates a copy from the builder
	 * 
	 * @param source
	 */
	public SQLQueryBuilder(SQLQueryBuilder source) {
		this();
		this.query = source.getQuery().clone();
		this.builderContext = source.getContext().clone();
	}

	/**
	 * Override this method to automatically access the internal builder methods
	 * and variables.
	 */
	public void build() {

	}

	/**
	 * Create a test for the value.
	 * 
	 * @see BuilderContext#test(String, Object, Predicate)
	 * @param key
	 * @param value
	 * @param predicate
	 * @return this builder
	 */
	public <T> SQLQueryBuilder given(String key, T value, Predicate<T> predicate) {
		this.getContext().given(key, value, predicate);
		return this;
	}

	/**
	 * Apply the 'select' expression.
	 * 
	 * @param expression
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this builder
	 */
	public SQLQueryBuilder select(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().select(expression);
		}
		return this;
	}

	/**
	 * Apply the 'from' expression.
	 * 
	 * @param expression
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this builder
	 */
	public SQLQueryBuilder from(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().from(expression);
		}
		return this;
	}

	/**
	 * Apply the 'where' expression.
	 * 
	 * @param expression
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this builder
	 */
	public SQLQueryBuilder where(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().where(expression);
		}
		return this;
	}

	/**
	 * Apply the 'group by' expression.
	 * 
	 * @param expression
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this builder
	 */
	public SQLQueryBuilder groupBy(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().groupBy(expression);
		}
		return this;
	}

	/**
	 * Apply the 'order by' expression.
	 * 
	 * @param expression
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this builder
	 */
	public SQLQueryBuilder orderBy(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().orderBy(expression);
		}
		return this;
	}

	/**
	 * Apply the method {@link SQLQueryTemplate#addParams(Object...)}
	 * 
	 * @param value
	 * @param tests
	 *            the names of the test results to evaluate if the operation
	 *            should be applied
	 * @return this instance
	 */
	public SQLQueryBuilder param(Object value, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().addParams(value);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public SQLQueryBuilder clone() {
		return new SQLQueryBuilder(this);
	}

	/**
	 * Calls the {@link SQLQueryTemplate#get()}
	 * 
	 * @return the query string
	 */
	public String get() {
		return getQuery().get();
	}

	/**
	 * Returns the current query instance;
	 * 
	 * @return the query template
	 */
	public SQLQueryTemplate getQuery() {
		return query;
	}

	/**
	 * Returns the {@link BuilderContext} instance for this builder.
	 * 
	 * @return the instance.
	 */
	protected BuilderContext getContext() {
		return builderContext;
	}

}