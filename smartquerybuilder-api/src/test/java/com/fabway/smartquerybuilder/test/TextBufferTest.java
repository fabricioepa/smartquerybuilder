package com.fabway.smartquerybuilder.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fabway.smartquerybuilder.TextBuffer;

public class TextBufferTest {
	TextBuffer tb1 = null;
	TextBuffer tb2 = null;

	@Before
	public void setUp() {
		tb1 = new TextBuffer();
		tb2 = new TextBuffer(100);
	}

	@Test
	public void testAddString() {
		// positive scope: appends on tail
		assertEquals("", tb1.toString());
		tb1.add("12");
		assertEquals("12", tb1.toString());
		tb1.add("3");
		assertEquals("123", tb1.toString());

		// negative scope (null-safe)
		tb1.add((String) null);
		assertEquals("123null", tb1.toString());
	}

	@Test
	public void testAddTextBuffer() {
		tb1.add("abc");
		tb2.add("def");
		assertEquals(3, tb1.length());
		assertEquals(3, tb2.length());

		tb1.add(tb2);
		assertEquals("abcdef", tb1.toString());
		assertEquals("def", tb2.toString());
	}

	@Test
	public void testAddStart() {
		tb1.add("abc");
		tb1.addStart("pre-");
		assertEquals("pre-abc", tb1.toString());
	}

	@Test
	public void testInsert() {
		tb1.add("abc");

		tb1.insert(0, "pre-");
		assertEquals("pre-abc", tb1.toString());

		tb1.insert(tb1.length(), "-post");
		assertEquals("pre-abc-post", tb1.toString());

		tb1.insert(tb1.length() / 2, "-middle-");
		assertEquals("pre-ab-middle-c-post", tb1.toString());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tb1.isEmpty());
		assertEquals(0, tb1.length());
		
		tb1.add(" ");
		assertFalse(tb1.isEmpty());
		assertEquals(1, tb1.length());
	}

	@Test
	public void testClear() {
		assertTrue(tb1.isEmpty());
		assertEquals(0, tb1.length());
		
		tb1.add("not empty");
		assertFalse(tb1.isEmpty());
		assertEquals(9, tb1.length());
		tb1.clear();
		
		assertTrue(tb1.isEmpty());
		assertEquals(0, tb1.length());
	}

	@Test
	public void testLength() {
		assertTrue(tb1.isEmpty());
		assertEquals(0, tb1.length());
		tb1.add("not empty");
		assertEquals(9, tb1.length());
		tb1.add("abc");
		assertEquals(12, tb1.length());
	}
	
	@Test
	public void testTextBufferCapacity() {
		assertTrue(tb1.isEmpty());
		//Default capacity is not ZERO
		assertTrue(new TextBuffer().capacity() > 0);
		assertEquals(1096, new TextBuffer(1096).capacity());
	}


}
