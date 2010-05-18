package com.jeebook.appengine.gtd.server.service;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Service {
	public String get(String id) throws ServiceException { return null; }
	public String create(String json) throws ServiceException { return null; }
	public String delete(String id) throws ServiceException { return null; }
	public String modify(String json) throws ServiceException { return null; }
	
	public User getUser() throws ServiceException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if ( user == null )
        	throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED, userService.createLoginURL("/Shuffle.html"));
        
        return user;
	}
}
