package com.fabway.smartquerybuilder;

import java.util.List;

public interface ITemplate {

    /**
     * Build and return the template. Calling this method will always build the
     * template.
     * 
     * @return the built string
     */
    String build();

    /**
     * Builds the template once if it was not built yet and returns the text.
     * Calling this method twice will not build the template again.
     * 
     * @return the built string
     */
    String get();

    /**
     * Returns the current positional parameters as a list.
     * 
     * @return the list
     */
    List<Object> getParameters();

    /**
     * Returns the current positional parameters as an array.
     * 
     * @return the parameters array
     */
    Object[] getParametersArray();

    /**
     * Adds positional parameters to template.
     * 
     * @param params
     */
    void addParameters(Object... params);

    /**
     * Returns the parameter in the position.
     * 
     * @param pos
     *            the parameter position
     * @return the parameter value
     * @throws IndexOutOfBoundsException
     */
    Object getParam(int pos);

    /**
     * Creates a copy of this template.
     * 
     * @return the new object
     */
    ITemplate copy();
}
