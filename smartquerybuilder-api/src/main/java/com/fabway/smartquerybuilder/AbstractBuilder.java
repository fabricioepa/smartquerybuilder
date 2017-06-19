package com.fabway.smartquerybuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * 
 * Default builder implementation.
 * 
 * @param <B>
 *            the concrete builder type
 * @param <T>
 *            The template type
 */
public abstract class AbstractBuilder<B extends IBuilder<B, T>, T extends ITemplate> implements IBuilder<B, T> {

    private T tpl = createTemplate();
    private BuilderContext context = createContext();

    protected AbstractBuilder() {
    }

    protected abstract B builder();

    protected abstract T createTemplate();

    protected BuilderContext createContext() {
        return new BuilderContext();
    }

    @Override
    public <O> B given(String key, O value, Predicate<O> predicate) {
        this.getContext().given(key, value, predicate);
        return this.builder();
    }

    @Override
    public <O> B g(String key, O value, Predicate<O> predicate) {
        this.given(key, value, predicate);
        return this.builder();
    }

    @Override
    public B param(Object value, String... conditions) {
        if (this.getContext().results(conditions)) {
            this.getTemplate().addParameters(value);
        }
        return this.builder();
    }

    @Override
    public B p(Object value, String... conditions) {
        return this.param(value, conditions);
    }

    @Override
    public <O> B givenParam(String key, O value, final Predicate<O> predicate) {
        this.given(key, value, predicate);
        this.param(value, key);
        return this.builder();
    }

    @Override
    public <O> B gp(String key, O value, final Predicate<O> predicate) {
        return this.givenParam(key, value, predicate);
    }

    @Override
    public String get() {
        return this.getTemplate().get();
    }

    @Override
    public String build() {
        return this.getTemplate().build();
    }

    @Override
    public List<Object> params() {
        return this.getTemplate().getParameters();
    }

    @Override
    public Object[] paramsAsArray() {
        return this.getTemplate().getParametersArray();
    }

    @Override
    public T getTemplate() {
        return this.tpl;
    }

    protected void setTemplate(T template) {
        this.tpl = template;
    }

    @Override
    public BuilderContext getContext() {
        return this.context;
    }

    protected void setContext(BuilderContext context) {
        this.context = context;
    }

}
