package com.fabway.smartquerybuilder.builders;

import java.util.ArrayList;
import java.util.List;

import com.fabway.smartquerybuilder.TextBuffer;

/**
 * Builds a statement using buffers instead of string concatenation.
 * 
 * @see StatementBuilder
 */
public class StatementTemplate {

    protected TextBuffer buffer = createBuffer();
    protected String statement = null;
    private List<Object> paramsList = createParamsList();

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
        this.paramsList.addAll(source.paramsList);
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

    /**
     * Add the parameters to the list.
     * 
     * @param params
     */
    public void addParams(Object... params) {
        if (params != null && params.length > 0) {
            for (Object param : params) {
                paramsList.add(param);
            }
        }
    }

    /**
     * Returns the current parameters as a list.
     * 
     * @return the list
     */
    public List<Object> getParamsList() {
        return paramsList;
    }

    /**
     * Returns the current parameters as an array.
     * 
     * @return the parameters array
     */
    public Object[] getParamsAsArray() {
        return paramsList.toArray();
    }

    /**
     * Sets the parameter list.
     * 
     * @param paramsList
     */
    public void setParamsList(List<Object> paramsList) {
        this.paramsList = paramsList;
    }

    /**
     * Returns the parameter in the position.
     * 
     * @param pos
     *            the parameter position
     * @return the parameter value
     * @throws IndexOutOfBoundsException
     */
    public Object getParam(int pos) {
        return paramsList.get(pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public StatementTemplate clone() {
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
    protected ArrayList<Object> createParamsList() {
        return new ArrayList<Object>();
    }

}