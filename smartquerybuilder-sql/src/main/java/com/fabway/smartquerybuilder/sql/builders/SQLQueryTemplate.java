package com.fabway.smartquerybuilder.sql.builders;

import com.fabway.smartquerybuilder.SmartBuffer;
import com.fabway.smartquerybuilder.builders.StatementTemplate;

/**
 * Extends {@link StatementTemplate} for creating SQL queries based on the SQL
 * SELECT COMMAND STRUCTURE:
 * 
 * <pre>
 * SELECT CLAUSE 
 * + FROM CLAUSE
 * + WHERE CLAUSE 
 * + GROUP BY CLAUSE 
 * + ORDER BY CLAUSE
 * </pre>
 * 
 * This class does not include any SQL terms in the built statement.
 * 
 */
public class SQLQueryTemplate extends StatementTemplate {
	
	private SmartBuffer select = createBuffer();

	private SmartBuffer from = createBuffer();

	private SmartBuffer where = createBuffer();

	private SmartBuffer groupBy = createBuffer();

	private SmartBuffer orderBy = createBuffer();

	/**
	 * Default constructor.
	 */
	public SQLQueryTemplate() {
	}

	/**
	 * Creates a copy from the source.
	 * 
	 * @param source
	 */
	public SQLQueryTemplate(SQLQueryTemplate source) {
		super(source);
		this.select = createBuffer(source.select);
		this.from = createBuffer(source.from);
		this.where = createBuffer(source.where);
		this.groupBy = createBuffer(source.groupBy);
		this.orderBy = createBuffer(source.orderBy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.compal.imd.core.application.repository.smartquerybuilder.domains.
	 * StatementTemplate#build()
	 */
	@Override
	public String build() {
		super.buffer.clear();
		super.buffer.add(select);
		super.buffer.add(from);
		super.buffer.add(where);
		super.buffer.add(groupBy);
		super.buffer.add(orderBy);
		return super.build();
	}

	/**
	 * Adds the text to the select clause buffer.
	 * 
	 * @param expression
	 */
	public void select(String expression) {
		select.add(expression);
	}

	/**
	 * Adds the text to the from clause buffer.
	 * 
	 * @param expression
	 */
	public void from(String expression) {
		from.add(expression);
	}

	/**
	 * Adds the text to the where clause buffer.
	 * 
	 * @param expression
	 */
	public void where(String expression) {
		where.add(expression);
	}

	/**
	 * Adds the text to the group by clause buffer if all found tests are true.
	 * 
	 * @param expression
	 *            the expression value
	 */
	public void groupBy(String expression) {
		groupBy.add(expression);
	}

	/**
	 * Adds the text to the order by clause buffer.
	 * 
	 * @param expression
	 *            the expression
	 */
	public void orderBy(String expression) {
		orderBy.add(expression);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public SQLQueryTemplate clone() {
		return new SQLQueryTemplate(this);
	}

	/**
	 * Returns the buffer for the SELECT clause.
	 * 
	 * @return the buffer
	 */
	public SmartBuffer getSelect() {
		return select;
	}

	/**
	 * Returns the buffer for the FROM clause.
	 * 
	 * @return the buffer
	 */
	public SmartBuffer getFrom() {
		return from;
	}

	/**
	 * Returns the buffer for the WHERE clause.
	 * 
	 * @return the buffer
	 */
	public SmartBuffer getWhere() {
		return where;
	}

	/**
	 * Returns the buffer for the GORUP BY clause.
	 * 
	 * @return the buffer
	 */
	public SmartBuffer getGroupBy() {
		return groupBy;
	}

	/**
	 * Returns the buffer for the ORDER BY clause.
	 * 
	 * @return the buffer
	 */
	public SmartBuffer getOrderBy() {
		return orderBy;
	}
}