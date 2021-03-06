package com.fabway.smartquerybuilder.sql.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.fabway.smartquerybuilder.sql.SQLFunctions;

public class SQLFunctionsTest {

    @Test
    public void testLike() {
        assertNull(SQLFunctions.Like.call(null));
        assertEquals("%input%", SQLFunctions.Like.call("input"));
    }

    @Test
    public void testLikeStart() {
        assertNull(SQLFunctions.LikeStart.call(null));
        assertEquals("%input", SQLFunctions.LikeStart.call("input"));
    }

    @Test
    public void testLikeEnd() {
        assertNull(SQLFunctions.LikeEnd.call(null));
        assertEquals("input%", SQLFunctions.LikeEnd.call("input"));
    }

    @Test
    public void testDefaultConstructorWorks() {
        assertNotNull(new SQLFunctions());
    }

}
