package com.jeebook.appengine.gtd.server.service;

import javax.servlet.http.HttpServletResponse;

import org.json.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginService extends Service {	

	@Override
	public String get(String id) throws ServiceException {
		//
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
		JSONObject jo = new JSONObject();
		try {
	    	if ( null == user ) {
	    		jo.put("url", userService.createLoginURL("/Shuffle.html"));
	    		throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED, jo.toString());
	    	}
			else
			{
				jo.put("nikename", user.getNickname());
				jo.put("email", user.getEmail());
				jo.put("url", userService.createLogoutURL("/index.html"));				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo.toString();
	}
}