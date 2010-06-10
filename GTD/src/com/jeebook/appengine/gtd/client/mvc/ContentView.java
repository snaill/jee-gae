package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.jeebook.appengine.gtd.client.AppEvents;

public class ContentView extends View {

	Grid<ModelData> actionGrid;
	Grid<ModelData> projectGrid;
	Grid<ModelData> contextGrid;
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
	  if ( MenuView.ACTION_TYPE.equals( mm.get("type") ) ){
		  activeActionGrid("");
	  }
		  
  }
  
  protected void activeActionGrid(String url) {
      ContentPanel panel = (ContentPanel) Registry.get(AppView.CENTER_PANEL);
	  if ( null == actionGrid ) {
		  actionGrid = new Grid<ModelData>(null, null);
		  panel.add(actionGrid);
	  }
	  
	  CardLayout layout = (CardLayout)panel.getLayout();
	  layout.setActiveItem(actionGrid);
  }
}
