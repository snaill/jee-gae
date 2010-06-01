package com.jeebook.appengine.gtd.server.service;

import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.jeebook.appengine.gtd.server.model.LoginValue;

public class LoginService extends Service {	

	@Override
	public String get(String pathInfo) throws ServiceException {
		//
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    LoginValue lv = new LoginValue();
		Gson gson = new Gson();

    	if ( null == user ) {
    		lv.setUrl(userService.createLoginURL("/Shuffle.html"));
    		throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED, gson.toJson(lv));
    	}
		else
		{
			lv.setUser(user);
			lv.setUrl(userService.createLogoutURL("/index.html"));
		}

		return gson.toJson(lv);
	}
}