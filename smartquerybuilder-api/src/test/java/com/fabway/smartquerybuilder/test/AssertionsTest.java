package com.fabway.smartquerybuilder.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.fabway.smartquerybuilder.Assertions;

public class AssertionsTest {

    @Test
    public void testIsTrue() {
        // positive scope
        assertTrue(Assertions.isTrue(true));
        assertTrue(Assertions.isTrue("true"));
        assertTrue(Assertions.isTrue(Boolean.TRUE));
        assertTrue(Assertions.isTrue(new Boolean(true)));

        // negative scope
        assertFalse(Assertions.isTrue(null));
        assertFalse(Assertions.isTrue("false"));
        assertFalse(Assertions.isTrue("abc"));
        assertFalse(Assertions.isTrue(false));
        assertFalse(Assertions.isTrue(Boolean.FALSE));
        assertFalse(Assertions.isTrue(new Boolean(false)));
    }

    @Test
    public void testIsFalse() {
        // positive scope
        assertTrue(Assertions.isFalse(false));
        assertTrue(Assertions.isFalse("false"));
        assertTrue(Assertions.isFalse("abc"));
        assertTrue(Assertions.isFalse(Boolean.FALSE));
        assertTrue(Assertions.isFalse(new Boolean(false)));

        // negative scope
        assertFalse(Assertions.isFalse(null));
        assertFalse(Assertions.isFalse("true"));
        assertFalse(Assertions.isFalse(true));
        assertFalse(Assertions.isFalse(Boolean.TRUE));
        assertFalse(Assertions.isFalse(new Boolean(true)));
    }

    @Test
    public void testIsNull() {
        assertTrue(Assertions.isNull(null));
        assertFalse(Assertions.isNull(new Object()));

    }

    @Test
    public void testNotNull() {
        assertFalse(Assertions.notNull(null));
        assertTrue(Assertions.notNull(new Object()));
    }

    @Test
    public void testIsType() {
        // null-safe tests
        assertFalse(Assertions.isType(null, Number.class));
        assertFalse(Assertions.isType(new Double(0), null));
        assertFalse(Assertions.isType(null, null));

        // positive scope
        assertTrue(Assertions.isType(new Double(0), Number.class));
        // negative scope
        assertFalse(Assertions.isType(new Double(0), Collection.class));
    }

    @Test
    public void testIsZero() {
        // null-safe test
        assertFalse(Assertions.isZero(null));

        // positive scope
        assertTrue(Assertions.isZero(0));
        assertTrue(Assertions.isZero("000"));
        assertTrue(Assertions.isZero(new Double(0.0)));
        assertTrue(Assertions.isZero(new Long(0)));
        assertTrue(Assertions.isZero(new BigDecimal(0)));

        // negative scope
        assertFalse(Assertions.isZero(1));
        assertFalse(Assertions.isZero("001"));
        assertFalse(Assertions.isZero(new Double(-1)));
        assertFalse(Assertions.isZero(Long.MAX_VALUE));
        assertFalse(Assertions.isZero(BigDecimal.ONE));
    }

    @Test
    public void testIsEmptyFailsafe() {
        // null-safe test
        assertTrue(Assertions.isEmpty((null)));
        try {
            assertTrue(Assertions.isEmpty((new Exception())));
            fail("Exception should not be a supported type");
        } catch (Exception e) {
            assertTrue("Exception for unsuported type", IllegalArgumentException.class.isAssignableFrom(e.getClass()));
        }
    }

    @Test
    public void testIsEmptyString() {
        // positive scope
        assertTrue(Assertions.isEmpty(""));
        assertTrue(Assertions.isEmpty(" "));
        assertTrue(Assertions.isEmpty("       "));

        // negative scope
        assertFalse(Assertions.isEmpty(" a "));
        assertFalse(Assertions.isEmpty("  -"));
        assertFalse(Assertions.isEmpty("-  "));
    }

    @Test
    public void testIsEmptyCollection() {
        // positive scope
        assertTrue(Assertions.isEmpty(new ArrayList<>()));

        List<String> list = new ArrayList<>();
        list.add("one");

        // negative scope
        assertFalse(Assertions.isEmpty(list));
    }

    @Test
    public void testIsEmptyArray() {
        // positive scope
        assertTrue(Assertions.isEmpty(new Object[] {}));

        // negative scope
        assertFalse(Assertions.isEmpty(new Object[] { 1 }));
    }
    
    @Test
    public void testDefaultConstructorWorks() {
        assertNotNull(new Assertions());
    }

}
