package com.fabway.smartquerybuilder.test;

import static com.fabway.smartquerybuilder.Conditions.False;
import static com.fabway.smartquerybuilder.Conditions.True;
import static com.fabway.smartquerybuilder.Conditions.and;
import static com.fabway.smartquerybuilder.Conditions.not;
import static com.fabway.smartquerybuilder.Conditions.or;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fabway.smartquerybuilder.Condition;
import com.fabway.smartquerybuilder.Conditions;

public class ConditionsTest {

    @Test
    public void testAnd() {
        // positive scope
        assertTrue(and(True, True).evaluate(true));
        assertTrue(and(False, False).evaluate(false));
        // negative scope
        assertNull(and());
        assertNull(and((Condition[])null));
        assertFalse(and(True, False).evaluate(true));
    }

    @Test
    public void testOr() {
        // positive scope
        assertTrue(or(True, True).evaluate(true));
        assertTrue(or(True, False).evaluate(true));
        assertTrue(or(False, True).evaluate(true));
        assertTrue(or(False, False).evaluate(false));

        // negative scope
        assertNull(or());
        assertNull(or((Condition[])null));
        assertFalse(or(False, False).evaluate(true));
        assertFalse(or(True, True).evaluate(false));
    }

    @Test
    public void testNot() {
        // positive scope
        assertTrue(not(True).evaluate(false));
        assertTrue(not(False).evaluate(true));

        // negative scope
        assertNull(not(null));
        assertFalse(not(True).evaluate(true));
        assertFalse(not(False).evaluate(false));
    }

    @Test
    public void testDefaultConstructorWorks() {
        assertNotNull(new Conditions());
    }
}
