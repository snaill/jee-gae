package com.jeebook.appengine.gtd.test.server;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ActionTest extends TestCase {
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
    public void testNew() {
//		ActionData ad = (ActionData)ActionData.createObject();
//		ad.setName("testAction1");
//		ad.setDetails("testAction1");
//		ad.setProjectId("2");
//		ad.setContextId(contextListBox.getValue(contextListBox.getSelectedIndex()));
//		Date dueTime = dueTimeDatePicker.getValue();
//		if ( null != dueTime )
//			ad.setDueDate(dueTime.toString());
		
//		new AjaxRequest(RequestBuilder.POST, "action/").send(new JSONObject(ad).toString());	

    }

    @Test
    public void testInsert2() {
        doTest();
    }
}