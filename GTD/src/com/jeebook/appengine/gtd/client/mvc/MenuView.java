/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.jeebook.appengine.gtd.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootPanel;
import com.jeebook.appengine.gtd.client.AppEvents;
import com.jeebook.appengine.gtd.client.TopPanel;

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
/*
    loader = new BaseTreeLoader<Folder>(new TreeModelReader<List<Folder>>());
    store = new TreeStore<Folder>(loader);

    tree = new TreePanel<Folder>(store);
    tree.getStyle().setLeafIcon(IconHelper.createStyle("tree-folder"));
    tree.setDisplayProperty("name");
    tree.setAutoSelect(true);
    tree.getSelectionModel().addSelectionChangedListener(
        new SelectionChangedListener<Folder>() {

          @Override
          public void selectionChanged(SelectionChangedEvent<Folder> se) {
            Folder f = (Folder) se.getSelection().get(0);
            AppEvent evt = new AppEvent(AppEvents.ViewMailItems, f);
            fireEvent(evt);
          }
        });

    menu.add(tree);
*/    return menu;
  }
  
  ContentPanel createReportMenu() {
    ContentPanel menu = new ContentPanel();
    menu.setAnimCollapse(false);
    menu.setHeading("Report"); 
    return menu;
  }
  
  protected void handleEvent(AppEvent event) {
  }
}
