package com.fabway.smartquerybuilder.jpa.builders;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fabway.smartquerybuilder.TextBuffer;
import com.fabway.smartquerybuilder.sql.builders.SQLQueryTemplate;

/**
 * JPQL query template.
 * 
 */
public class JPQLQueryTemplate extends SQLQueryTemplate {
    private Map<String, Object> namedParameters = new HashMap<String, Object>();

    /**
     * Default constructor.
     */
    public JPQLQueryTemplate() {
        super();
    }

    /**
     * Creates a copy of the source
     * 
     * @param source
     */
    public JPQLQueryTemplate(JPQLQueryTemplate source) {
        super(source);
        this.namedParameters = new HashMap<>(source.namedParameters);
    }

    /**
     * Sets a named parameter.
     * 
     * @param name
     * @param value
     */
    public void setParameter(String name, Object value) {
        namedParameters.put(name, value);
    }

    /**
     * Adds a new AND conjunction to the WHERE clause.
     * 
     * @param expression
     */
    public void and(String expression) {
        TextBuffer where = getWhere();
        if (!where.isEmpty()) {
            where.add(" and ");
        }
        where.add(expression);
    }

    /**
     * Adds a new OR conjunction to the WHERE clause.
     * 
     * @param expression
     */
    public void or(String expression) {
        TextBuffer where = getWhere();
        if (!where.isEmpty()) {
            where.add(" or ");
        }
        where.add(expression);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.compal.imd.core.application.repository.smartquerybuilder.domains.
     * SQLQueryTemplate#build()
     */
    @Override
    public String build() {
        mountSelect();
        mountFrom();
        mountWhere();
        mountGroupBy();
        mountOrderBy();
        return super.build();
    }

    private void mountSelect() {
        TextBuffer select = getSelect();
        if (!select.isEmpty()) {
            select.addStart("select ");
        }
    }

    private void mountFrom() {
        TextBuffer from = getFrom();
        if (!from.isEmpty()) {
            from.addStart(" from ");
        }
    }

    private void mountWhere() {
        TextBuffer where = getWhere();
        if (!where.isEmpty()) {
            where.addStart(" where ");
        }
    }

    private void mountGroupBy() {
        TextBuffer groupBy = getGroupBy();
        if (!groupBy.isEmpty()) {
            groupBy.addStart(" group by ");
        }
    }

    private void mountOrderBy() {
        TextBuffer orderBy = getOrderBy();
        if (!orderBy.isEmpty()) {
            orderBy.addStart(" order by ");
        }
    }

    @Override
    public JPQLQueryTemplate copy() {
        return new JPQLQueryTemplate(this);
    }

    /**
     * Returns the current named parameters.
     * 
     * @return the parameters map
     */
    public Map<String, Object> getNamedParameters() {
        return namedParameters;
    }

    /**
     * Creates a typed query using entity manager.
     * 
     * @param em
     * @param resultClass
     * @return the query
     */
    public <T> TypedQuery<T> createQuery(EntityManager em, Class<T> resultClass) {
        String jpaquery = get();
        TypedQuery<T> query = em.createQuery(jpaquery, resultClass);

        for (Entry<String, Object> parameter : namedParameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        Object[] paramsAsArray = this.getParametersArray();
        for (int pos = 0; pos < paramsAsArray.length; pos++) {
            query.setParameter(pos + 1, paramsAsArray[pos]);
        }

        return query;
    }
}