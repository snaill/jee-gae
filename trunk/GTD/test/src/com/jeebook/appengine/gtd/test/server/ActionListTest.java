package com.jeebook.appengine.gtd.test.server;

import java.lang.reflect.Type;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import com.google.gson.reflect.TypeToken;
import com.jeebook.appengine.gtd.server.model.ActionValue;
import com.jeebook.appengine.gtd.server.service.ActionService;


public class ActionListTest extends LoggedInBaseTest {
    String addAction( String name ) {
    	String		response = "";
    	try {
    		ActionValue cv = new ActionValue();
    		cv.setName(name);
			
			ActionService service = new ActionService();
			response = service.create(gson.toJson(cv));
			
			ActionValue cv2 = gson.fromJson(response, ActionValue.class);
			Assert.assertEquals(name, cv2.getName());
			Assert.assertNotNull(cv2.getId());
			
			return cv2.getId();
		} catch (Exception e) {
			Assert.fail();
		}
		
		return null;
    }
 
    @Test
    public void testGetAll() {
    	addAction("testAction1");
    	addAction("testAction2");
    	addAction("testAction3");
    	String id = addAction("testAction4");
    	addAction("testAction5");
    	addAction("testAction6");
    	
    	try {
			ActionService service = new ActionService();
			String response = service.get(null);

			Type type = new TypeToken<List<ActionValue>>(){}.getType(); 
			List<ActionValue> cvs = gson.fromJson(response, type);
			Assert.assertEquals(6, cvs.size());
			Assert.assertEquals(id, cvs.get(3).getId());
			Assert.assertEquals("testAction4", cvs.get(3).getName());
		} catch (Exception e) {
			Assert.fail();
		}
    }
}