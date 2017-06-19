package com.fabway.smartquerybuilder.test;

import static com.fabway.smartquerybuilder.Conditions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConditionsTest {

    @Test
    public void testAnd() {
        // positive scope
        assertTrue(and(True, True).evaluate(true));
        assertTrue(and(False, False).evaluate(false));
        // negative scope
        assertNull(and());
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

}
