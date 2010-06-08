package com.jeebook.appengine.gtd.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class MenuModel extends BaseTreeModel {

  private static int ID = 0;
  public static final String ACTION_TYPE = "action";
  public static final String PROJECT_TYPE = "project";
  public static final String CONTEXT_TYPE = "context";
  public static final String REPORT_TYPE = "report";
  
  public MenuModel() {
    set("id", ID++);
  }

  public MenuModel(String name, String type) {
    set("id", ID++);
    set("name", name);
    set("type", type);
  }

  public MenuModel(String name, String type, BaseTreeModel[] children) {
    this(name, type);
    for (int i = 0; i < children.length; i++) {
      add(children[i]);
    }
  }

  public Integer getId() {
    return (Integer) get("id");
  }

  public String getName() {
    return (String) get("name");
  }

  public String getType() {
    return (String) get("type");
  }
  
  public String toString() {
    return getName();
  }
}