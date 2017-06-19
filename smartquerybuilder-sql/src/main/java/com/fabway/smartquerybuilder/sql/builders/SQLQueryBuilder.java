package com.fabway.smartquerybuilder.sql.builders;

import com.fabway.smartquerybuilder.AbstractBuilder;

/**
 * Builder for creating and configuring the {@link SQLQueryTemplate} object.
 */
public class SQLQueryBuilder extends AbstractBuilder<SQLQueryBuilder, SQLQueryTemplate> {
    /**
     * Default constructor.
     */
    public SQLQueryBuilder() {
        super();
    }

    /**
     * Creates a copy from the builder
     * 
     * @param source
     */
    public SQLQueryBuilder(SQLQueryBuilder source) {
        super.setContext(source.getContext().copy());
        super.setTemplate(source.getTemplate().copy());
    }

    /**
     * Apply the 'select' expression.
     * 
     * @param expression
     * @param conditions
     *            the precondition keys
     * @return this builder
     */
    public SQLQueryBuilder select(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().select(expression);
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
    public SQLQueryBuilder from(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().from(expression);
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
    public SQLQueryBuilder where(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().where(expression);
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
    public SQLQueryBuilder groupBy(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().groupBy(expression);
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
    public SQLQueryBuilder orderBy(String expression, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().orderBy(expression);
        }
        return this;
    }

    @Override
    public SQLQueryBuilder copy() {
        return new SQLQueryBuilder(this);
    }

    @Override
    protected SQLQueryBuilder builder() {
        return this;
    }

    @Override
    protected SQLQueryTemplate createTemplate() {
        return new SQLQueryTemplate();
    }
}