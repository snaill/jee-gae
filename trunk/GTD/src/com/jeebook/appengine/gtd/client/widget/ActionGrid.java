package com.jeebook.appengine.gtd.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.JsonLoadResultReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelType;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.LiveGridView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;

public class ActionGrid extends ContentPanel {
	public ActionGrid(String url) {
      List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  	    
      ColumnConfig title = new ColumnConfig("title", "Topic", 100);  
      title.setRenderer(new GridCellRenderer<ModelData>() {  

		@Override
		public Object render(ModelData model, String property,
				ColumnData config, int rowIndex, int colIndex,
				ListStore<ModelData> store, Grid<ModelData> grid) {
	          return "<b><a style=\"color: #385F95; text-decoration: none;\" href=\"http://extjs.com/forum/showthread.php?t="  
              + model.get("threadid")  
              + "\" target=\"_blank\">"  
              + model.get("title")  
              + "</a></b><br /><a style=\"color: #385F95; text-decoration: none;\" href=\"http://extjs.com/forum/forumdisplay.php?f="  
              + model.get("forumid") + "\" target=\"_blank\">" + model.get("forumtitle") + " Forum</a>";  
		}  
    
      });  
      columns.add(title);  
      columns.add(new ColumnConfig("replycount", "Replies", 50));  
    
      ColumnConfig last = new ColumnConfig("lastpost", "Last Post", 200);  
      last.setRenderer(new GridCellRenderer<ModelData>() {  
    
        public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
            ListStore<ModelData> store, Grid<ModelData> grid) {  
          return model.get("lastpost") + "<br/>by " + model.get("lastposter");  
        }  
    
      });  
      columns.add(last); 
      
    // create the column model  
    ColumnModel cm = new ColumnModel(columns);  
	  
    // defines the xml structure  
    ModelType type = new ModelType();  
    type.setRoot("records");  
    type.addField("Sender", "name");  
    type.addField("Email", "email");  
    type.addField("Phone", "phone");  
    type.addField("State", "state");  
    type.addField("Zip", "zip");  
  
    // use a http proxy to get the data  
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, GWT.getModuleBaseURL() + url);  
    HttpProxy<String> proxy = new HttpProxy<String>(builder);  
  
    // need a loader, proxy, and reader  
    JsonLoadResultReader<PagingLoadResult<ModelData>> reader = new JsonLoadResultReader<PagingLoadResult<ModelData>>(type);  
  
    final PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy,  
            reader);
    
    loader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {  
        public void handleEvent(LoadEvent be) {  
          BasePagingLoadConfig m = be.<BasePagingLoadConfig> getConfig();  
          m.set("start", m.get("offset"));  
          m.set("ext", "js");  
          m.set("lightWeight", true);  
          m.set("sort", (m.get("sortField") == null) ? "" : m.get("sortField"));  
          m.set("dir", (m.get("sortDir") == null || (m.get("sortDir") != null && m.<SortDir> get("sortDir").equals(  
              SortDir.NONE))) ? "" : m.get("sortDir"));  
    
        }  
      });  
      loader.setSortDir(SortDir.DESC);  
      loader.setSortField("lastpost");      
      loader.setRemoteSort(true);  
    
      //
      ListStore<ModelData> store = new ListStore<ModelData>(loader);  
    
      //
      Grid<ModelData> grid = new Grid<ModelData>(store, cm);  
      grid.setTrackMouseOver(false);  
      grid.setBorders(true);  
      grid.setAutoExpandColumn("title");  
      grid.getSelectionModel().setLocked(true);  
    
      LiveGridView liveView = new LiveGridView();  
      liveView.setEmptyText("No rows available on the server.");  
      liveView.setRowHeight(32);  
      grid.setView(liveView);  
    
      setFrame(true);  
      setCollapsible(true);  
      setAnimCollapse(false);  
 //     setIcon(Resources.ICONS.table());  
      setHeaderVisible(false);
//      setHeading("LiveGrid Grid");  
      setLayout(new FitLayout());  
      add(grid);  
//      setSize(600, 350);  
    
//      ToolBar toolBar = new ToolBar();  
//      toolBar.add(new FillToolItem());  
    
//      LiveToolItem item = new LiveToolItem();  
//      item.bindGrid(grid);  
    
//      toolBar.add(item);  
//      setBottomComponent(toolBar);  
	}
}
