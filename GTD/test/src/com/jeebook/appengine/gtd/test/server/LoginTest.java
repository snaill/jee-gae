package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

import com.jeebook.appengine.gtd.server.service.LoginService;

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
			String response = service.get("");
			
			JSONObject jo = new JSONObject(response);
			Assert.assertNotNull(jo.get("url"));
			Assert.assertFalse(jo.has("email"));
			
		} catch (Exception e) {
			Assert.fail();
		}
    }
}