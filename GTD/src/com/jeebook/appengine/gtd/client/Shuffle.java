package com.jeebook.appengine.gtd.client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.jeebook.appengine.gtd.client.mvc.AppController;
import com.jeebook.appengine.gtd.client.mvc.MenuController;

public class Shuffle implements EntryPoint {
	
  /**
   * This method constructs the application user interface by instantiating
   * controls and hooking up event handler.
   */
  public void onModuleLoad() {
	  GXT.setDefaultTheme(Theme.BLUE, true);
	  
	  Dispatcher dispatcher = Dispatcher.get();
	  dispatcher.addController(new AppController());
	  dispatcher.addController(new MenuController());
	    
	  dispatcher.dispatch(AppEvents.Init);
	  GXT.hideLoadingPanel("loading");
	  
  }
}
