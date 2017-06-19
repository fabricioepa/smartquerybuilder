package com.fabway.smartquerybuilder.test;

import static org.junit.Assert.*;
import static com.fabway.smartquerybuilder.Predicates.*;

import org.junit.Before;
import org.junit.Test;

import com.fabway.smartquerybuilder.BuilderContext;

public class BuilderContextTest {

	BuilderContext bc = null;

	@Before
	public void setUp() {
		this.bc = new BuilderContext();
		bc.given("c1_true", 1, NotNull);
		bc.given("c2_true", 0, IsZero);
		bc.given("c3_false", "", not(Empty));
		bc.given("c4_false", " ", not(Empty));
	}

	@Test
	public void testGiven() {
		assertTrue(bc.given("XXXX", null, Null));
		assertFalse(bc.given("XXXX", null, not(Null)));
	}

	@Test
	public void testResults() {
		assertTrue(bc.results("c1_true", "c2_true")); // all true
		assertFalse(bc.results("c1_true", "c2_true", "c3_false")); // one false
		assertFalse(bc.results("c3_false", "c4_false"));// all false
	}

	@Test
	public void testResult() {
		assertTrue(bc.result("c1_true"));
		assertFalse(bc.result("c4_false"));
	}

	@Test
	public void testCopy() {
		BuilderContext bc2 = bc.copy();
		
		//both contexts are equal
		assertTrue(bc.result("c1_true"));
		assertTrue(bc2.result("c1_true"));
		
		//must evaluate to false
		bc2.given("c1_true", null, (val) -> val != null); 
		
		//original context not modified
		assertTrue(bc.result("c1_true"));
		assertFalse(bc2.result("c1_true"));
	}

}
