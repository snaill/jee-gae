package com.jeebook.appengine.gtd.test.server;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for com.jeebook.appengine.gtd.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(ActionTest.class);
		suite.addTestSuite(ActionListTest.class);
		suite.addTestSuite(ProjectTest.class);
		suite.addTestSuite(ContextTest.class);
		suite.addTestSuite(LoginTest.class);
		//$JUnit-END$
		return suite;
	}
}
