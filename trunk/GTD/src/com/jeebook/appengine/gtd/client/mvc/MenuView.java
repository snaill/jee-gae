/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.jeebook.appengine.gtd.client.AppEvents;

public class MenuView extends View {

	  public static final String ACTION_TYPE = "action";
	  public static final String PROJECT_TYPE = "project";
	  public static final String CONTEXT_TYPE = "context";
	  public static final String REPORT_TYPE = "report";

  public MenuView(Controller controller) {
    super(controller);
  }

  protected void initialize() {
    ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
    west.add(createActionMenu());
    west.add(createReportMenu());   
  }

  ContentPanel createActionMenu() {
    ContentPanel menu = new ContentPanel();
    menu.setAnimCollapse(false);
    menu.setHeading("Actions");
 //   menu.addListener(Events.Expand, new Listener<ComponentEvent>() {
 //     public void handleEvent(ComponentEvent be) {
 //       Dispatcher.get().dispatch(AppEvents.NavMail);
 //     }
 //   });

    TreeStore<ModelData> store = new TreeStore<ModelData>();
    store.add(createModel("Inbox", ACTION_TYPE), false);
    store.add(createModel("Next Action", ACTION_TYPE), false);
    store.add(createModel("Waiting for", ACTION_TYPE), false);
    store.add(createModel("Someday", ACTION_TYPE), false);
    store.add(createModel("Project", PROJECT_TYPE), false);
    store.add(createModel("Context", CONTEXT_TYPE), false);

    TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
    tree.getStyle().setLeafIcon(IconHelper.createStyle("tree-folder"));
    tree.setDisplayProperty("name");
    tree.setAutoSelect(true);
    tree.getSelectionModel().addSelectionChangedListener(
        new SelectionChangedListener<ModelData>() {

          @Override
          public void selectionChanged(SelectionChangedEvent<ModelData> se) {
        	  BaseModelData f = (BaseModelData) se.getSelection().get(0);
              Dispatcher.get().dispatch(AppEvents.MenuSelected, f);
          }
        });

    menu.add(tree);
    return menu;
  }
  
  ContentPanel createReportMenu() {
    ContentPanel menu = new ContentPanel();
    menu.setAnimCollapse(false);
    menu.setHeading("Report"); 
    
    TreeStore<ModelData> store = new TreeStore<ModelData>();
    store.add(createModel("Last week", REPORT_TYPE), false);
    store.add(createModel("Last month", REPORT_TYPE), false);
    store.add(createModel("Last year", REPORT_TYPE), false);

    TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
    tree.getStyle().setLeafIcon(IconHelper.createStyle("tree-folder"));
    tree.setDisplayProperty("name");
    tree.setAutoSelect(true);
    tree.getSelectionModel().addSelectionChangedListener(
        new SelectionChangedListener<ModelData>() {

          @Override
          public void selectionChanged(SelectionChangedEvent<ModelData> se) {
        	  BaseModelData f = (BaseModelData) se.getSelection().get(0);
              Dispatcher.get().dispatch(AppEvents.MenuSelected, f);
          }
        });

    menu.add(tree);
    return menu;
  }
  
  protected ModelData createModel(String name, String type ) {
	  BaseModelData bmd = new BaseModelData();
	  bmd.set("name", name);
	  bmd.set("type", type);
	  return bmd;
  }
  protected void handleEvent(AppEvent event) {
  }
}
