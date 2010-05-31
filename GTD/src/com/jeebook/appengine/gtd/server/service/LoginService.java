package com.jeebook.appengine.gtd.server.service;

import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.JsonObject;

public class LoginService extends Service {	

	@Override
	public String get(String pathInfo) throws ServiceException {
		//
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
		JsonObject jo = new JsonObject();

    	if ( null == user ) {
    		jo.addProperty("url", userService.createLoginURL("/Shuffle.html"));
    		throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED, jo.toString());
    	}
		else
		{
			jo.addProperty("nikename", user.getNickname());
			jo.addProperty("email", user.getEmail());
			jo.addProperty("url", userService.createLogoutURL("/index.html"));				
		}

		return jo.toString();
	}
}