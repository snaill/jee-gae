package com.jeebook.appengine.gtd.test.server;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.jeebook.appengine.gtd.server.service.ContextServlet;

public class ContextTest extends LoggedInBaseTest {

    @Test
    public void testNew() {
    	UserService us = UserServiceFactory.getUserService();
    	JSONObject	jo = new JSONObject();
    	String		response;
    	try {
			jo.put("name", "testContext");
			
			ContextServlet servlet = new ContextServlet();
			User user = us.getCurrentUser();
			response = servlet.New(user, jo.toString());
//			response = sendRequest("context/", "POST", jo.toString());
			
			jo = new JSONObject(response);
			Assert.assertEquals("testContext", jo.get("name"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

    @Test
    public void testInsert2() {

    }
}