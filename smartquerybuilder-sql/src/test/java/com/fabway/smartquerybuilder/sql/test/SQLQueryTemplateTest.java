package com.fabway.smartquerybuilder.sql.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.fabway.smartquerybuilder.TextBuffer;
import com.fabway.smartquerybuilder.sql.builders.SQLQueryTemplate;

public class SQLQueryTemplateTest {

    SQLQueryTemplate tpl;

    @Before
    public void setUp() {
        this.tpl = new SQLQueryTemplate();
    }

    private void verifyTemplateBuffers(SQLQueryTemplate template, Function<SQLQueryTemplate, TextBuffer> getBuffer,
            BiConsumer<SQLQueryTemplate, String> call) {
        assertTrue(getBuffer.apply(template).isEmpty());
        assertEquals("", getBuffer.apply(template).toString());
        call.accept(tpl, "expr1");
        assertEquals("expr1", getBuffer.apply(template).toString());
        call.accept(tpl, "expr2");
        assertEquals("expr1expr2", getBuffer.apply(template).toString());
    }

    @Test
    public void testGetSelect() {
        verifyTemplateBuffers(this.tpl, (tpl) -> tpl.getSelect(), (tpl, expr) -> tpl.select(expr));
    }

    @Test
    public void testGetFrom() {
        verifyTemplateBuffers(this.tpl, (tpl) -> tpl.getFrom(),  (tpl, expr) -> tpl.from(expr));
    }

    @Test
    public void testGetWhere() {
        verifyTemplateBuffers(this.tpl, (tpl) -> tpl.getWhere(),  (tpl, expr) -> tpl.where(expr));
    }

    @Test
    public void testGetGroupBy() {
        verifyTemplateBuffers(this.tpl, (tpl) -> tpl.getGroupBy(),  (tpl, expr) -> tpl.groupBy(expr));
    }

    @Test
    public void testGetOrderBy() {
        verifyTemplateBuffers(this.tpl, (tpl) -> tpl.getOrderBy(),  (tpl, expr) -> tpl.orderBy(expr));
    }

}
