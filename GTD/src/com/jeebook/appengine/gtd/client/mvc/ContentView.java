package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.jeebook.appengine.gtd.client.AppEvents;
import com.jeebook.appengine.gtd.client.model.MenuModel;

public class ContentView extends View {

	Grid<MenuModel> actionGrid;
	Grid<MenuModel> projectGrid;
	Grid<MenuModel> contextGrid;
	Grid<MenuModel> reportGrid;
	
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
	  MenuModel mm = (MenuModel)event.getData();
	  if ( MenuModel.ACTION_TYPE.equals( mm.getType() ) ){
		  activeActionGrid("");
	  }
		  
  }
  
  protected void activeActionGrid(String url) {
      ContentPanel panel = (ContentPanel) Registry.get(AppView.CENTER_PANEL);
	  if ( null == actionGrid ) {
		  actionGrid = new Grid<MenuModel>(null, null);
		  panel.add(actionGrid);
	  }
	  
	  CardLayout layout = (CardLayout)panel.getLayout();
	  layout.setActiveItem(actionGrid);
  }
}
