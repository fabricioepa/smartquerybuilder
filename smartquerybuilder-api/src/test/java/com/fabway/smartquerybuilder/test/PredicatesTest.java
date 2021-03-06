package com.fabway.smartquerybuilder.test;

import static com.fabway.smartquerybuilder.Predicates.False;
import static com.fabway.smartquerybuilder.Predicates.True;
import static com.fabway.smartquerybuilder.Predicates.and;
import static com.fabway.smartquerybuilder.Predicates.not;
import static com.fabway.smartquerybuilder.Predicates.or;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.junit.Test;

import com.fabway.smartquerybuilder.Predicates;

public class PredicatesTest {

    @Test
    public void testAnd() {
        // positive scope
        assertTrue(and(True, True).test(true));
        assertTrue(and(False, False).test(false));
        // negative scope
        assertNull(and());
        assertNull(and((Predicate<Object>[]) null));
        assertFalse(and(True, False).test(true));
    }

    @Test
    public void testOr() {
        // positive scope
        assertTrue(or(True, True).test(true));
        assertTrue(or(True, False).test(true));
        assertTrue(or(False, True).test(true));
        assertTrue(or(False, False).test(false));

        // negative scope
        assertNull(or());
        assertNull(or((Predicate<Object>[])null));
        assertFalse(or(False, False).test(true));
        assertFalse(or(True, True).test(false));
    }

    @Test
    public void testNot() {
        // positive scope
        assertTrue(not(True).test(false));
        assertTrue(not(False).test(true));

        // negative scope
        assertNull(not(null));
        assertFalse(not(True).test(true));
        assertFalse(not(False).test(false));
    }
    
    @Test
    public void testDefaultConstructorWorks() {
        assertNotNull(new Predicates());
    }

}
