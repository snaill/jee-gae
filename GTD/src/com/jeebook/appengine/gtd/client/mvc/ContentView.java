package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.jeebook.appengine.gtd.client.AppEvents;
import com.jeebook.appengine.gtd.client.widget.ActionGrid;
import com.jeebook.appengine.gtd.client.widget.ActionTreeGrid;

public class ContentView extends View {

	ActionGrid actionGrid;
	ActionTreeGrid projectGrid;
	ActionTreeGrid contextGrid;
	Grid<ModelData> reportGrid;
	
  public ContentView(Controller controller) {
    super(controller);
  }

  protected void initialize() {
  }

  protected void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type == AppEvents.MenuSelected) {
      onMenuSelected(event);
    }
  }
  
  protected void onMenuSelected(AppEvent event) {
	  ModelData mm = (ModelData)event.getData();
	  if ( MenuView.INBOX_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("inbox");
	  } else if ( MenuView.PROJECT_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("");
	  } else if ( MenuView.CONTEXT_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("");
	  } else if ( MenuView.INBOX_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("");
	  } else if ( MenuView.INBOX_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("");
	  }	  
  }
  
  protected void activeActionGrid(String url) {
	  LayoutContainer panel = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
	  if ( null == actionGrid ) {
		  actionGrid = new ActionGrid(url);//new Grid<ModelData>(null, null);
		  panel.add(actionGrid);
	  }
	  
	  CardLayout layout = (CardLayout)panel.getLayout();
	  layout.setActiveItem(actionGrid);
  } 
  
  protected void activeProjectGrid(String url) {
	  LayoutContainer panel = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
	  if ( null == projectGrid ) {
		  projectGrid = new ActionTreeGrid();//new Grid<ModelData>(null, null);
		  panel.add(projectGrid);
	  }
	  
	  CardLayout layout = (CardLayout)panel.getLayout();
	  layout.setActiveItem(projectGrid);
  }
}
