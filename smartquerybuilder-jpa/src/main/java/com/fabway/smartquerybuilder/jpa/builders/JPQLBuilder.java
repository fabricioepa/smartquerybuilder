package com.fabway.smartquerybuilder.jpa.builders;

import java.util.Map;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fabway.smartquerybuilder.AbstractBuilder;
import com.fabway.smartquerybuilder.BuilderContext;

/**
 * Builder for creating and configuring the {@link JPQLQueryTemplate} object.
 */
public class JPQLBuilder extends AbstractBuilder<JPQLBuilder, JPQLQueryTemplate> {

    /**
     * Default constructor.
     */
    public JPQLBuilder() {
        super();
    }

    /**
     * Creates a copy from the builder.
     * 
     * @param source
     */
    public JPQLBuilder(JPQLBuilder source) {
        super.setContext(source.getContext().copy());
        super.setTemplate(source.getTemplate().copy());
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
            getTemplate().select(expression);
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
            getTemplate().from(expression);
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
            getTemplate().where(expression);
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
            getTemplate().groupBy(expression);
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
            getTemplate().orderBy(expression);
        }
        return this;
    }

    public JPQLBuilder and(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            getTemplate().and(expression);
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
            getTemplate().or(expression);
        }
        return this;
    }

    /**
     * Create a test for the parameter value. If the test succeeded a test
     * result is stored using the parameter name as key.
     * 
     * 
     * 
     * Shortcut for calling methods {@link #given(String, Object, Predicate)}
     * and {@link #param(Object, String...)}. For the example bellow:
     * 
     * <pre>
     *  .given("pName", name, not(Empty))
     *  .param(name, "pName")
     *  .from("...")
     *  .where("name = :name", "pName")
     * </pre>
     * 
     * You can only call:
     * 
     * <pre>
     *  .givenParam("pName", name, not(Empty))
     *  .from("...")
     *  .where("name = :name", "pName")
     * </pre>
     * 
     * 
     * @param paramKey
     *            the parameter name will be used as a precondition key
     * @param paramValue
     * @param predicate
     * @return this builder
     * @see BuilderContext#given(String, Object, Predicate)
     */
    @Override
    public <T> JPQLBuilder givenParam(String paramKey, T paramValue, final Predicate<T> predicate) {
        this.given(paramKey, paramValue, predicate);
        this.param(paramKey, paramValue, paramKey);
        return this.builder();
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
            getTemplate().setParameter(name, value);
        }
        return this;
    }

    /**
     * Apply the method
     * {@link JPQLQueryTemplate#createQuery(EntityManager, Class)}
     * 
     * @param em
     * @param resultClass
     * @return this builder
     */
    public <T> TypedQuery<T> createQuery(EntityManager em, Class<T> resultClass) {
        return getTemplate().createQuery(em, resultClass);
    }

    /**
     * Alias to {@link JPQLQueryTemplate#getNamedParameters()}
     * 
     * @return the named parameters
     */
    public Map<String, Object> namedParams() {
        return this.getTemplate().getNamedParameters();
    }

    @Override
    public JPQLBuilder copy() {
        return new JPQLBuilder(this);
    }

    @Override
    protected JPQLBuilder builder() {
        return this;
    }

    @Override
    protected JPQLQueryTemplate createTemplate() {
        return new JPQLQueryTemplate();
    }

}