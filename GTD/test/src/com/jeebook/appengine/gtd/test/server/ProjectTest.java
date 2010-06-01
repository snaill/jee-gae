package com.jeebook.appengine.gtd.test.server;

import java.lang.reflect.Type;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import com.google.gson.reflect.TypeToken;
import com.jeebook.appengine.gtd.server.model.ProjectValue;
import com.jeebook.appengine.gtd.server.service.ProjectService;


public class ProjectTest extends LoggedInBaseTest {

    @Test
    public void testNew() {
    	addProject("testProject");
    }

    String addProject( String name ) {
    	String		response = "";
    	try {
    		ProjectValue cv = new ProjectValue();
    		cv.setName(name);
			
			ProjectService service = new ProjectService();
			response = service.create(gson.toJson(cv));
			
			ProjectValue cv2 = gson.fromJson(response, ProjectValue.class);
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
    	
    	String id = addProject("testProject1");
    	
    	try {
			ProjectService service = new ProjectService();
			String response = service.get(id);
			
			Type type = new TypeToken<List<ProjectValue>>(){}.getType(); 
			List<ProjectValue> cvs = gson.fromJson(response, type);
			Assert.assertEquals(1, cvs.size());
			Assert.assertEquals(id, cvs.get(0).getId());
			Assert.assertEquals("testProject1", cvs.get(0).getName());
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
    
    @Test
    public void testGetAll() {
    	addProject("testProject1");
    	addProject("testProject2");
    	addProject("testProject3");
    	String id = addProject("testProject4");
    	addProject("testProject5");
    	addProject("testProject6");
    	
    	try {
			ProjectService service = new ProjectService();
			String response = service.get(null);

			Type type = new TypeToken<List<ProjectValue>>(){}.getType(); 
			List<ProjectValue> cvs = gson.fromJson(response, type);
			Assert.assertEquals(6, cvs.size());
			Assert.assertEquals(id, cvs.get(3).getId());
			Assert.assertEquals("testProject4", cvs.get(3).getName());
		} catch (Exception e) {
			Assert.fail();
		}
    }
}