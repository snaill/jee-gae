package com.jeebook.appengine.gtd.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class MenuModel extends BaseTreeModel {

  private static int ID = 0;
  
  public MenuModel() {
    set("id", ID++);
  }

  public MenuModel(String name) {
    set("id", ID++);
    set("name", name);
  }

  public MenuModel(String name, BaseTreeModel[] children) {
    this(name);
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

  public String toString() {
    return getName();
  }
}