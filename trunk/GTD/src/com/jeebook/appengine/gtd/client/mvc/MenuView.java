/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
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
import com.jeebook.appengine.gtd.client.model.MenuModel;

public class MenuView extends View {

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

    TreeStore<MenuModel> store = new TreeStore<MenuModel>();
    store.add(new MenuModel("Inbox", MenuModel.ACTION_TYPE), false);
    store.add(new MenuModel("Next Action", MenuModel.ACTION_TYPE), false);
    store.add(new MenuModel("Waiting for", MenuModel.ACTION_TYPE), false);
    store.add(new MenuModel("Someday", MenuModel.ACTION_TYPE), false);
    store.add(new MenuModel("Project", MenuModel.PROJECT_TYPE), false);
    store.add(new MenuModel("Context", MenuModel.CONTEXT_TYPE), false);

    TreePanel<MenuModel> tree = new TreePanel<MenuModel>(store);
    tree.getStyle().setLeafIcon(IconHelper.createStyle("tree-folder"));
    tree.setDisplayProperty("name");
    tree.setAutoSelect(true);
    tree.getSelectionModel().addSelectionChangedListener(
        new SelectionChangedListener<MenuModel>() {

          @Override
          public void selectionChanged(SelectionChangedEvent<MenuModel> se) {
        	  MenuModel f = (MenuModel) se.getSelection().get(0);
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
    
    TreeStore<MenuModel> store = new TreeStore<MenuModel>();
    store.add(new MenuModel("Last week", MenuModel.REPORT_TYPE), false);
    store.add(new MenuModel("Last month", MenuModel.REPORT_TYPE), false);
    store.add(new MenuModel("Last year", MenuModel.REPORT_TYPE), false);

    TreePanel<MenuModel> tree = new TreePanel<MenuModel>(store);
    tree.getStyle().setLeafIcon(IconHelper.createStyle("tree-folder"));
    tree.setDisplayProperty("name");
    tree.setAutoSelect(true);
    tree.getSelectionModel().addSelectionChangedListener(
        new SelectionChangedListener<MenuModel>() {

          @Override
          public void selectionChanged(SelectionChangedEvent<MenuModel> se) {
        	  MenuModel f = (MenuModel) se.getSelection().get(0);
              Dispatcher.get().dispatch(AppEvents.MenuSelected, f);
          }
        });

    menu.add(tree);
    return menu;
  }
  
  protected void handleEvent(AppEvent event) {
  }
}
