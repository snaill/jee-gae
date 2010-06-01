package com.jeebook.appengine.gtd.test.server;

import java.lang.reflect.Type;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import com.google.gson.reflect.TypeToken;
import com.jeebook.appengine.gtd.server.model.ContextValue;
import com.jeebook.appengine.gtd.server.service.ContextService;

public class ContextTest extends LoggedInBaseTest {

    @Test
    public void testNew() {
    	addContext("testContext");
    }

    String addContext( String name ) {
    	String		response = "";
    	try {
    		ContextValue cv = new ContextValue();
    		cv.setName(name);
			
			ContextService service = new ContextService();
			response = service.create(gson.toJson(cv));
			
			ContextValue cv2 = gson.fromJson(response, ContextValue.class);
			Assert.assertEquals(name, cv2.getName());
			Assert.assertNotNull(cv2.getId());
			
			return cv2.getId();
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
			
			Type type = new TypeToken<List<ContextValue>>(){}.getType(); 
			List<ContextValue> cvs = gson.fromJson(response, type);
			Assert.assertEquals(1, cvs.size());
			Assert.assertEquals(id, cvs.get(0).getId());
			Assert.assertEquals("testContext1", cvs.get(0).getName());
			
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

			Type type = new TypeToken<List<ContextValue>>(){}.getType(); 
			List<ContextValue> cvs = gson.fromJson(response, type);
			Assert.assertEquals(6, cvs.size());
			Assert.assertEquals(id, cvs.get(3).getId());
			Assert.assertEquals("testContext4", cvs.get(3).getName());
		} catch (Exception e) {
			Assert.fail();
		}
    }
}