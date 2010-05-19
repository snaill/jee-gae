package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.jeebook.appengine.gtd.server.service.LoginService;
import com.jeebook.appengine.gtd.server.service.ServiceException;

public class LoginTest extends LoggedInBaseTest {

    @Test
    public void testLoggedin() {
    	try {
			LoginService service = new LoginService();
			String response = service.get("");
			
			JSONObject jo = new JSONObject(response);
			Assert.assertNotNull(jo.get("url"));
			Assert.assertEquals("test@gmail.com", jo.get("email"));
			
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
    		
    		JSONObject jo;
			try {
				jo = new JSONObject(se.getMessage());

				Assert.assertFalse(jo.has("email"));
				Assert.assertNotNull(jo.get("url"));
			} catch (JSONException e) {
				Assert.fail();
			}
		} catch (Exception e) {
			Assert.fail();
		}
    }
}