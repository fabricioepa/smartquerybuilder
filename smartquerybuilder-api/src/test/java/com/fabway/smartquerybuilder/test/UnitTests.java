package com.fabway.smartquerybuilder.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssertionsTest.class, 
	    StatementTemplateTest.class,
	    PredicatesTest.class, 
	    BuilderContextTest.class,
		StatementBuilderTest.class, 
		ConditionsTest.class })
public class UnitTests {

}
