package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;
import org.junit.Test;
import com.jeebook.appengine.gtd.server.model.LoginValue;
import com.jeebook.appengine.gtd.server.service.LoginService;
import com.jeebook.appengine.gtd.server.service.ServiceException;

public class LoginTest extends LoggedInBaseTest {

    @Test
    public void testLoggedin() {
    	try {
			LoginService service = new LoginService();
			String response = service.get("");
			
			LoginValue lv = gson.fromJson(response, LoginValue.class); 
			Assert.assertNotNull(lv.getUrl());
			Assert.assertEquals("test@gmail.com", lv.getUser().getEmail());
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
    
    @Test
    public void testNotLoggedin() {
    	getHelper().setEnvIsLoggedIn(false);
    	try {
			LoginService service = new LoginService();
			service.get(null);
			
    	} catch (ServiceException se) {
    		Assert.assertEquals(401, se.getStatus());

    		LoginValue lv = gson.fromJson(se.getMessage(), LoginValue.class); 
			Assert.assertNull(lv.getUser());
			Assert.assertNotNull(lv.getUrl());
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
}