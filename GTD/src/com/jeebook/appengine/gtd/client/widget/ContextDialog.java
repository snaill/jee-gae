package com.jeebook.appengine.gtd.client.widget;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.json.client.JSONObject;
import com.jeebook.appengine.gtd.client.model.ContextData;
import com.jeebook.appengine.gtd.client.service.AjaxRequest;

public class ContextDialog extends FormDialog {
	
	TextField<String> mNameTextField;
	Button			  mSaveButton;
	Button			  mCloseButton;	
	
	public ContextDialog(ContextData pd) {
		super("Context");
		setHeight(150);
		
		mNameTextField = new TextField<String>();  
	    mNameTextField.setFieldLabel("Name");  
	    mNameTextField.setAllowBlank(false); 
	    mNameTextField.addKeyListener(new KeyListener(){

	    	public void handleEvent(ComponentEvent e) {
	    		super.handleEvent(e);
	    		switch ( e.getKeyCode() ) {
    			case KeyCodes.KEY_ENTER:
    					mSaveButton.fireEvent(Events.Select);
    				break;
    				default:
	    	    		if ( mNameTextField.getValue() != null )
	    	    		{
	    	    			if ( !mSaveButton.isEnabled() )
	    	    				mSaveButton.enable();
	    	    		}
	    	    		else {
	    	    			if ( mSaveButton.isEnabled() )
	    	    				mSaveButton.disable();
	    	    		}	
  		
	    	}}
	    });
	    mNameTextField.focus();
	    addField(mNameTextField);  
	}
	
	  @Override
	  protected void createButtons() {
		  super.createButtons();
	    
	    mSaveButton = new Button("Save");
	    mSaveButton.disable();
	    mSaveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
	    	  begin();
			ContextData cd = (ContextData)ContextData.createObject();
			cd.setName(mNameTextField.getValue());
		    new AjaxRequest(null, RequestBuilder.POST, "context/") {
				
				@Override
				public void onSuccess(Object param, String response){
					reset();
				}
			}.send(new JSONObject(cd).toString());
	      }
	    });
	    addButton(mSaveButton);
	    setDefaultButton(mSaveButton);

	    mCloseButton = new Button("Close");
	    mCloseButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
				endDialog();
	      }
	    });
	    addButton(mCloseButton);
	  } 
	  
	  void reset() {
			end();
		    mSaveButton.disable();
			mNameTextField.clear();
			mNameTextField.focus();
	  }
}
