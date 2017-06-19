package com.fabway.smartquerybuilder.builders;

import java.util.ArrayList;
import java.util.List;

import com.fabway.smartquerybuilder.ITemplate;
import com.fabway.smartquerybuilder.TextBuffer;

/**
 * Builds a statement using buffers instead of string concatenation.
 * 
 * @see StatementBuilder
 */
public class StatementTemplate implements ITemplate {

    protected TextBuffer buffer = createBuffer();
    protected String statement = null;
    private List<Object> parameters = createParametersList();

    /**
     * Default constructor.
     */
    public StatementTemplate() {
    }

    /**
     * Creates a copy from the source.
     * 
     * @param source
     */
    public StatementTemplate(StatementTemplate source) {
        this();
        this.statement = source.statement;
        this.buffer = createBuffer(source.buffer);
        this.parameters.addAll(source.parameters);
    }

    /**
     * Build and return the statement. Calling this method will always update
     * current built statement value.
     * 
     * @return the built string
     */
    public String build() {
        this.statement = buffer.toString();
        return statement;
    }

    /**
     * Builds the statement once if it was not built yet and returns the
     * statement. Calling this method twice will not build the statement again.
     * 
     * @return the built statement
     */
    public String get() {
        if (statement == null) {
            return build();
        }
        return getBuiltStatement();
    }

    /**
     * Returns the statement if built, otherwise returns null.
     * 
     * @return the statement
     */
    public String getBuiltStatement() {
        return statement;
    }

    /**
     * Appends the string in the buffer.
     * 
     * @param text
     */
    public void put(String text) {
        buffer.add(text);
    }

    @Override
    public void addParameters(Object... params) {
        if (params != null && params.length > 0) {
            for (Object param : params) {
                parameters.add(param);
            }
        }
    }

    @Override
    public List<Object> getParameters() {
        return parameters;
    }

    @Override
    public Object[] getParametersArray() {
        return parameters.toArray();
    }

    /**
     * Sets the parameter list.
     * 
     * @param parameters
     */
    public void setParamsList(List<Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object getParam(int pos) {
        return parameters.get(pos);
    }

    @Override
    public StatementTemplate copy() {
        return new StatementTemplate(this);
    }

    /**
     * Creates the string buffer default.
     * 
     * @return the buffer
     */
    protected TextBuffer createBuffer() {
        return new TextBuffer();
    }

    /**
     * Creates a copy from the buffer.
     * 
     * @param source
     *            the source
     * @return the copy
     */
    protected TextBuffer createBuffer(TextBuffer source) {
        return new TextBuffer(source);
    }

    /**
     * Creates the parameter list default.
     * 
     * @return
     */
    protected ArrayList<Object> createParametersList() {
        return new ArrayList<Object>();
    }

}