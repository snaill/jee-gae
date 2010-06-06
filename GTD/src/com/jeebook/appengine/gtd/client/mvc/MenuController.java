/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.jeebook.appengine.gtd.client.AppEvents;

public class MenuController extends Controller {

	MenuView view;
	
  public MenuController() {
    registerEventTypes(AppEvents.Init);
  }
  
  public void initialize() {
	  view = new MenuView(this);
  }

  @Override
  public void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type == AppEvents.Init) {
      forwardToView(view, event);
    }
  }
}
