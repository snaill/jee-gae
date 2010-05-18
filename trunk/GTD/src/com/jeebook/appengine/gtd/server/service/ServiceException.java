package com.jeebook.appengine.gtd.server.service;

public class ServiceException extends Exception {
	int mStatus;
	String mMessage;
	
	public ServiceException( int status, String msg ) {
		mStatus = status;
		mMessage = msg;
	}
	
	public int getStatus() {
		return mStatus;
	}
	
	public String getMessage() {
		return mMessage;
	}
}
