package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

import com.jeebook.appengine.gtd.server.service.ContextService;

public class ContextTest extends LoggedInBaseTest {

    @Test
    public void testNew() {
    	JSONObject	jo = new JSONObject();
    	String		response = "";
    	try {
			jo.put("name", "testContext");
			
			ContextService service = new ContextService();
			response = service.create(jo.toString());
			
			jo = new JSONObject(response);
			Assert.assertEquals("testContext", jo.get("name"));
			
		} catch (Exception e) {
			Assert.fail();
		}
    }

    @Test
    public void testInsert2() {

    }
}