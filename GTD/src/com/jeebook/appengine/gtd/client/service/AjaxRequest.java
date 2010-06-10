package com.jeebook.appengine.gtd.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

public class AjaxRequest {
	RequestBuilder 	mBuilder; 
	Object			mParam;
	
	public AjaxRequest( Object param, RequestBuilder.Method methed, String url ) {
		mParam = param;
		mBuilder = new RequestBuilder(methed, GWT.getModuleBaseURL() + url);
		mBuilder.setCallback(new RequestCallback(){

			@Override
			public void onError(Request request, Throwable exception) {
				onFail(exception.getLocalizedMessage());
			}
			
			@Override
			public void onResponseReceived(Request request, Response response) {

				switch ( response.getStatusCode() ) {
					case Response.SC_OK:
						onSuccess(mParam, response.getText());
						break;
					case Response.SC_UNAUTHORIZED:{
						String json = response.getText();
						JSONValue respValue = JSONParser.parse(json);
						JSONObject jo = respValue.isObject();
						String url = jo.get("url").isString().stringValue();
						redirect(url);
						break;
					}
				}
			}
		});
	}

	public void send(String requestData) {
		mBuilder.setRequestData(requestData);
		try{
			mBuilder.send();
		} catch(RequestException e){
			onFail(e.getLocalizedMessage());
		}
	}

	public void onSuccess(Object param, String response){}
	public void onFail(String text){
		Window.alert(text);
	}
	public static native void redirect(String url)/*-{ $wnd.location = url;	}-*/; 
}

