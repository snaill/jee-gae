package com.jeebook.appengine.gtd.client.model;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.client.JavaScriptObject;

public class ProjectData extends JavaScriptObject {
	protected ProjectData() {}
	
	public final native String getId() /*-{ return this.id; }-*/;
	public final native void setId( String id ) /*-{ this.id = id; }-*/;
	public final native String getName() /*-{ return this.name; }-*/;
	public final native void setName( String name ) /*-{ this.name = name; }-*/;
	
	public final ModelData toModelData() {
		BaseModelData bmd = new BaseModelData();
		bmd.set("id", getId());
		bmd.set("name", getName());
		return bmd;
	}
}