package com.jeebook.appengine.gtd.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class LoginData extends JavaScriptObject {
	protected LoginData() {}
	
	public final native String getUrl() /*-{ return this.url; }-*/;
	public final native String getEmail() /*-{ 
		if ( null == this.user )
			return "";
		return this.user.email; 
	}-*/;
	
	/**
	 * Convert the string of JSON into JavaScript object.
	 */
	public final static LoginData fromJson(String json) {
		JSONValue jv = JSONParser.parse(json);
		return (LoginData)jv.isObject().getJavaScriptObject();
  	};
}