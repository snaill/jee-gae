package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.jeebook.appengine.gtd.client.AppEvents;

public class AppController extends Controller {

	AppView		view;
	
	public AppController() {
	    registerEventTypes(AppEvents.Init);
	    registerEventTypes(AppEvents.Login);
	}
	
	public void initialize() {
		view = new AppView(this);
	}
	  
	@Override
	public void handleEvent(AppEvent event) {
	    EventType type = event.getType();
	    if (type == AppEvents.Init) {
	      forwardToView(view, event);
	    } else if (type == AppEvents.Init) {
	      forwardToView(view, event);
	    }
	}
}
