package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;

import org.json.JSONArray;
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

    String addContext( String name ) {
    	JSONObject	jo = new JSONObject();
    	String		response = "";
    	try {
			jo.put("name", name);
			
			ContextService service = new ContextService();
			response = service.create(jo.toString());
			
			jo = new JSONObject(response);
			Assert.assertNotNull(jo.get("id"));
			
			return jo.getString("id");
		} catch (Exception e) {
			Assert.fail();
		}
		
		return null;
    }
    
    @Test
    public void testGet() {
    	
    	String id = addContext("testContext1");
    	
    	try {
			ContextService service = new ContextService();
			String response = service.get(id);
			
			JSONArray ja = new JSONArray( response );
			Assert.assertEquals(1, ja.length());
			JSONObject jo = (JSONObject)ja.get(0);
			Assert.assertEquals(id, jo.get("id"));
			Assert.assertEquals("testContext1", jo.get("name"));
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
    
    @Test
    public void testGetAll() {
    	addContext("testContext1");
    	addContext("testContext2");
    	addContext("testContext3");
    	String id = addContext("testContext4");
    	addContext("testContext5");
    	addContext("testContext6");
    	
    	try {
			ContextService service = new ContextService();
			String response = service.get(null);
			
			JSONArray ja = new JSONArray( response );
			Assert.assertEquals(6, ja.length());
			JSONObject jo = (JSONObject)ja.get(3);
			Assert.assertEquals(id, jo.get("id"));
			Assert.assertEquals("testContext4", jo.get("name"));
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
}