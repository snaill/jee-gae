package com.jeebook.appengine.gtd.server.model;

import com.google.appengine.api.users.User;

public class LoginValue {
	
	private User user;
    
	private String url;
	
	public final User getUser() {
		return user;
	}

	public final String getUrl() {
		return url;
	}
	
	public final void setUser( User u ) {
		user = u;
	}
	
	public final void setUrl( String u ) {
		url = u;
	}
}
