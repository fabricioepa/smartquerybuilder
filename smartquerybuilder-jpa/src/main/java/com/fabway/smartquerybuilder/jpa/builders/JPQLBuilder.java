package com.fabway.smartquerybuilder.jpa.builders;

import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fabway.smartquerybuilder.BuilderContext;

/**
 * Builder for creating and configuring the {@link JPQLQueryTemplate} object.
 * Usage:
 * 
 * <pre>{@code
 * Long roleId = null;
 * JPQLBuilder query = new JPQLBuilder()
 * 		.given("roleId", roleId, not(IsZero), not(Null))
 *      .given("falseCondition", false, True)
 * 		.from("from Users u")
 * 		.from(" inner join Role role on role.id = u.role_id ", "roleId")
 * 		.and(" u.id > 0")
 * 		.and(" and role.id = ? ", "roleId")
 *      .or(" this text will not be added", "falseCondition");
 * 		
 * String sqlCount = query.clone().select("count(u.id)").get();
 * String sqlSelect = query.select("select u.*").get();
 * }
 * </pre>
 * 
 */
public class JPQLBuilder  {
	private JPQLQueryTemplate query = new JPQLQueryTemplate();
	private BuilderContext builderContext = new BuilderContext();

	/**
	 * Default constructor.
	 */
	public JPQLBuilder() {
		super();
		build();
	}

	/**
	 * Creates a copy from the builder.
	 * 
	 * @param source
	 */
	public JPQLBuilder(JPQLBuilder source) {
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
	 *  Adds a precondition in the context.
	 * 
	 * @see  BuilderContext#given(String, Object, Predicate)
	 * @param key
	 * @param value
	 * @param predicate
	 * @return this builder
	 */
	public <T> JPQLBuilder given(String key, T value, Predicate<T> predicate) {
		this.getContext().given(key, value, predicate);
		return this;
	}

	/**
	 * Create a test for the parameter value. If the test succeeded a test
	 * result is stored using the parameter name as key.
	 * 
	 * @param paramName
	 * @param paramValue
	 * @param condition
	 * @return this builder
	 * @see BuilderContext#given(String, Object, Predicate)
	 */
	public <T> JPQLBuilder givenParam(String paramName, T paramValue, final Predicate<T> condition) {
		String testName = paramName;
		this.getContext().given(testName, paramValue, condition);
		param(paramName, paramValue, testName);
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
	public JPQLBuilder select(String expression, String... tests) {
		if (this.getContext().results(tests)) {
			getQuery().select(expression);
		}
		return this;
	}

	/**
	 * Apply the 'from' expression.
	 * 
	 * @param expression
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public JPQLBuilder from(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().from(expression);
		}
		return this;
	}

	/**
	 * Apply the 'where' expression.
	 * 
	 * @param expression
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public JPQLBuilder where(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().where(expression);
		}
		return this;
	}

	/**
	 * Apply the 'group by' expression.
	 * 
	 * @param expression
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public JPQLBuilder groupBy(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().groupBy(expression);
		}
		return this;
	}

	/**
	 * Apply the 'order by' expression.
	 * 
	 * @param expression
	 * @param conditions
	 *            the precondition keys
	 * @return this builder
	 */
	public JPQLBuilder orderBy(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().orderBy(expression);
		}
		return this;
	}

	public JPQLBuilder and(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().and(expression);
		}
		return this;
	}

	/**
	 * Apply the method {@link JPQLQueryTemplate#or(String)}
	 * 
	 * @param expression
	 * @param conditions
	 *            the preconditions
	 * @return this builder
	 */
	public JPQLBuilder or(String expression, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().or(expression);
		}
		return this;
	}

	/**
	 * Apply the method {@link JPQLQueryTemplate#setParameter(String, Object)}
	 * 
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the parameter value
	 * @param conditions
	 *            the preconditions
	 * @return this builder
	 */
	public JPQLBuilder param(String name, Object value, String... conditions) {
		if (this.getContext().results(conditions)) {
			getQuery().setParameter(name, value);
		}
		return this;
	}

	/**
	 * Apply the method {@link JPQLQueryTemplate#addParams(Object...)}
	 * 
	 * @param value
	 * @param conditions
	 *            the preconditions
	 * @return this instance
	 */
	public JPQLBuilder param(Object value, String... conditions) {
		if (this.getContext().results(conditions)) {
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
	public JPQLBuilder clone() {
		return new JPQLBuilder(this);
	}

	public JPQLQueryTemplate getQuery() {
		return query;
	}

	public <T> TypedQuery<T> createQuery(EntityManager em, Class<T> resultClass) {
		return getQuery().createQuery(em, resultClass);
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