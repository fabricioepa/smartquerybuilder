package com.fabway.smartquerybuilder.builders;

import com.fabway.smartquerybuilder.AbstractBuilder;
import com.fabway.smartquerybuilder.BuilderContext;

/**
 * Builds a statement using preconditions.
 */
public class StatementBuilder extends AbstractBuilder<StatementBuilder, StatementTemplate> {

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
        super.setContext(source.getContext().copy());
        super.setTemplate(source.getTemplate().copy());
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
        if (getContext().results(conditions)) {
            getTemplate().put(text);
        }
        return this;
    }

    @Override
    public StatementBuilder copy() {
        return new StatementBuilder(this);
    }

    protected StatementBuilder builder() {
        return this;
    }

    @Override
    protected StatementTemplate createTemplate() {
        return new StatementTemplate();
    }

    @Override
    protected BuilderContext createContext() {
        return new BuilderContext();
    }

}