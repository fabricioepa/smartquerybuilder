package com.fabway.smartquerybuilder.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fabway.smartquerybuilder.ITemplate;
import com.fabway.smartquerybuilder.builders.StatementTemplate;

public class StatementTemplateTest {

    StatementTemplate emptyTpl;
    StatementTemplate tpl;

    @Before
    public void setUp() {
        this.emptyTpl = new StatementTemplate();
        this.tpl = new StatementTemplate();
        this.tpl.put("NOT EMPTY");

    }

    @Test
    public void testBuild() {
        assertEquals("", emptyTpl.build());
        assertEquals("NOT EMPTY", tpl.build());
    }

    @Test
    public void testGet() {
        assertEquals("", emptyTpl.get());
        emptyTpl.put("NOT EMPTY NOW");
        assertEquals("", emptyTpl.get());
        this.emptyTpl.build();
        assertEquals("NOT EMPTY NOW", emptyTpl.get());
    }

    @Test
    public void testGetBuiltStatement() {
        assertEquals(null, emptyTpl.getBuiltStatement());
        emptyTpl.put("BUILT");
        assertEquals(null, emptyTpl.getBuiltStatement());
        emptyTpl.build();
        assertEquals("BUILT", emptyTpl.getBuiltStatement());

    }

    @Test
    public void testGetParameters() {
        assertTrue(emptyTpl.getParameters().isEmpty());
        emptyTpl.addParameters("1", "2", "3");
        @SuppressWarnings("serial")
        List<String> list = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
            }
        };

        assertEquals(list, emptyTpl.getParameters());
    }

    @Test
    public void testGetParametersAsArray() {
        assertArrayEquals(new Object[] {}, emptyTpl.getParametersArray());
        emptyTpl.addParameters(1, 2, 3);
        assertArrayEquals(new Object[] { 1, 2, 3 }, emptyTpl.getParametersArray());
    }

    @Test
    public void testAddAndGetParam() {
        try {
            assertNull(this.emptyTpl.getParam(0));
            fail("should throw IndexOutofBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue("param not exists", true);
        }
        this.emptyTpl.addParameters(1, 2);
        assertEquals(1, this.emptyTpl.getParam(0));
        assertEquals(2, this.emptyTpl.getParam(1));

        this.emptyTpl.addParameters();
        assertEquals(2, this.emptyTpl.getParameters().size());
    }

    @Test
    public void testSetParam() {
        List<Object> paramsList = new ArrayList<>();
        paramsList.add("testSet1");

        assertNotEquals(paramsList, this.emptyTpl.getParameters());
        this.emptyTpl.setParamsList(paramsList);
        assertEquals(paramsList, this.emptyTpl.getParameters());

        this.emptyTpl.setParamsList(null);
        assertNull(this.emptyTpl.getParameters());

    }

    @Test
    public void testCopy() {
        ITemplate tpl2 = this.tpl.copy();

        assertEquals(tpl2.build(), this.tpl.build());

        this.tpl.put("tpl1");

        assertNotEquals(tpl2.build(), this.tpl.build());
    }

}
