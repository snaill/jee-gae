package com.jeebook.appengine.gtd.server.service;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

public class Service {
    Gson gson = new Gson();

	public String get(String pathInfo) throws ServiceException { return null; }
	public String create(String json) throws ServiceException { return null; }
	public String delete(String pathInfo) throws ServiceException { return null; }
	public String modify(String json) throws ServiceException { return null; }
	
	protected User getUser() throws ServiceException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if ( user == null )
        	throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED, userService.createLoginURL("/Shuffle.html"));
        
        return user;
	}
	
	protected Long getId( String pathInfo ){
		String strId = pathInfo;
		if ( null == strId || strId.equals("/") )
			return null;
		
		if ( pathInfo.startsWith("/"))
			strId = pathInfo.substring(1);
		return Long.parseLong(strId);
	}
}
