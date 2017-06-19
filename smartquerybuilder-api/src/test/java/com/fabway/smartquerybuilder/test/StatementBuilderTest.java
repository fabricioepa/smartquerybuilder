package com.fabway.smartquerybuilder.test;

import static com.fabway.smartquerybuilder.Predicates.IsZero;
import static com.fabway.smartquerybuilder.Predicates.NotNull;
import static com.fabway.smartquerybuilder.Predicates.Null;
import static com.fabway.smartquerybuilder.Predicates.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fabway.smartquerybuilder.BuilderContext;
import com.fabway.smartquerybuilder.ITemplate;
import com.fabway.smartquerybuilder.builders.StatementBuilder;

public class StatementBuilderTest {

    StatementBuilder sb = null;
    Object arg1 = null;
    Object arg2 = new Object();
    Object arg3 = null;
    Object arg4 = new Object();

    @Before
    public void setUp() {
        this.sb = new StatementBuilder().given("given1", arg1, Null).given("given2", arg2, Null)
                .given("given3", arg3, NotNull).given("given4", arg4, NotNull);
    }

    @Test
    public void testGiven() {
        Object a1 = null;
        Object a2 = new Object();
        Object a3 = null;
        Object a4 = new Object();

        StatementBuilder sb = new StatementBuilder().given("g1", a1, Null).given("g2", a2, not(IsZero))
                .g("g3", a3, not(Null)).g("g4", a4, not(Null));

        BuilderContext context = sb.getContext();

        // positive scope: context populated
        assertTrue(context.result("g1"));
        assertTrue(context.result("g2"));
        assertFalse(context.result("g3"));
        assertTrue(context.result("g4"));

        // negative scope
        assertFalse(context.result(null));
        assertFalse(context.result("not_exists"));

    }

    @Test
    public void testParam() {
        Object arg1 = null;
        Object arg2 = new Object();
        Object arg3 = null;
        Object arg4 = new Object();
        Object arg5 = new Object();

        sb.param(arg1, "given1") // evaluates true
                .param(arg2, "given2").p(arg3, "given3").p(arg4, "given4") // evaluates
                                                                           // true
                .p(arg5, "not_exists");

        assertArrayEquals(new Object[] { arg1, arg4 }, sb.paramsAsArray());

    }

    @Test
    public void testGivenParam() {
        sb.givenParam("g1", (Object) arg1, Null).givenParam("g2", arg2, Null).gp("g3", (Object) arg3, NotNull)
                .gp("g4", arg4, NotNull);

        BuilderContext context = sb.getContext();

        // positive scope: context populated
        assertTrue(context.result("g1"));
        assertFalse(context.result("g2"));
        assertFalse(context.result("g3"));
        assertTrue(context.result("g4"));

        assertArrayEquals(new Object[] { arg1, arg4 }, sb.paramsAsArray());
    }

    @Test
    public void testPut() {
        sb.put("FIRST LINE").put(" EXP1 is true", "given1").put(" EXP2 is false", "given2")
                .put(" EXP3 is false", "given3").put(" EXP4 is true ", "given4").put(" LAST LINE");

        ITemplate tpl = sb.getTemplate();
        String text = tpl.get();

        assertTrue(text.contains("FIRST LINE"));
        assertTrue(text.contains("EXP1"));
        assertFalse(text.contains("EXP2"));
        assertFalse(text.contains("EXP3"));
        assertTrue(text.contains("EXP4"));
        assertTrue(text.contains(" LAST LINE"));
        assertTrue(sb.params().isEmpty());
    }

    @Test
    public void testCopy() {
        StatementBuilder sb2 = this.sb.copy();

        assertEquals(sb2.get(), this.sb.get());

        this.sb.put("new data");

        assertNotEquals(sb2.build(), this.sb.build());

    }
}
