package com.jeebook.appengine.gtd.client.model;

import com.google.gwt.core.client.JavaScriptObject;

public class ActionData extends JavaScriptObject {
	protected ActionData() {}

	public final native String getId() /*-{ return this.id; }-*/;
	public final native void setId( String id ) /*-{ this.id = id; }-*/;
	public final native String getName() /*-{ return this.name; }-*/;
	public final native void setName( String name ) /*-{ this.name = name; }-*/;
	public final native String getDetails() /*-{ return this.details; }-*/;
	public final native void setDetails( String details ) /*-{ this.details = details; }-*/;
	public final native ProjectData getProject() /*-{ return this.project; }-*/;
	public final native void setProject( ProjectData pd ) /*-{ this.project = pd; }-*/;
	public final native ContextData getContext() /*-{ return this.contextId; }-*/;
	public final native void setContext( ContextData cd ) /*-{ this.context = cd; }-*/;
	public final native String getDueDate() /*-{ return this.dueDate; }-*/;
	public final native void setDueDate( String dueDate ) /*-{ this.dueDate = dueDate; }-*/;
	public final native String getFinishDate() /*-{ return this.finishDate; }-*/;
	public final native void setFinishDate( String finishDate ) /*-{ this.finishDate = finishDate; }-*/;
}