package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.jeebook.appengine.gtd.client.AppEvents;

public class ContentController extends Controller {

	ContentView view;
	
  public ContentController() {
    registerEventTypes(AppEvents.Init);
    registerEventTypes(AppEvents.MenuSelected);
  }
  
  public void initialize() {
	  view = new ContentView(this);
  }

  @Override
  public void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type == AppEvents.MenuSelected) {
      forwardToView(view, event);
    }
  }
}
