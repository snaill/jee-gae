package com.jeebook.appengine.gtd.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ActionTest {
    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    // run this test twice to prove we're not leaking any state across tests
    private void doTest() {
//        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//        assertEquals(0, ds.prepare(new Query("yam")).countEntities());
//        ds.put(new Entity("yam"));
//        ds.put(new Entity("yam"));
//        assertEquals(2, ds.prepare(new Query("yam")).countEntities());
    }

    @Test
    public void testInsert1() {
        doTest();
    }

    @Test
    public void testInsert2() {
        doTest();
    }
}