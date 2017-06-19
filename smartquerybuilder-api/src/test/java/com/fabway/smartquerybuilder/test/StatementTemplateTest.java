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
	public void testGetParamsList() {
		assertTrue(emptyTpl.getParamsList().isEmpty());
		emptyTpl.addParams("1", "2", "3");
		@SuppressWarnings("serial")
		List<String> list = new ArrayList<String>() {
			{
				add("1");
				add("2");
				add("3");
			}
		};

		assertEquals(list, emptyTpl.getParamsList());
	}

	@Test
	public void testGetParamsAsArray() {
		assertArrayEquals(new Object[] {}, emptyTpl.getParamsAsArray());
		emptyTpl.addParams(1, 2, 3);
		assertArrayEquals(new Object[] { 1, 2, 3 }, emptyTpl.getParamsAsArray());
	}

	@Test
	public void testAddAndGetParam() {
		try {
			assertNull(this.emptyTpl.getParam(0));
			fail("should throw IndexOutofBoundsException");
		} catch (IndexOutOfBoundsException ex) {
			assertTrue("param not exists", true);
		}
		this.emptyTpl.addParams(1, 2);
		assertEquals(1, this.emptyTpl.getParam(0));
		assertEquals(2, this.emptyTpl.getParam(1));

		this.emptyTpl.addParams();
		assertEquals(2, this.emptyTpl.getParamsList().size());
	}

	@Test
	public void testSetParam() {
		List<Object> paramsList = new ArrayList<>();
		paramsList.add("testSet1");

		assertNotEquals(paramsList, this.emptyTpl.getParamsList());
		this.emptyTpl.setParamsList(paramsList);
		assertEquals(paramsList, this.emptyTpl.getParamsList());

		this.emptyTpl.setParamsList(null);
		assertNull(this.emptyTpl.getParamsList());

	}

	@Test
	public void testClone() {
		StatementTemplate tpl2 = this.tpl.clone();

		assertEquals(tpl2.build(), this.tpl.build());

		this.tpl.put("tpl1");

		assertNotEquals(tpl2.build(), this.tpl.build());
	}

}
