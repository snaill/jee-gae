package com.jeebook.appengine.gtd.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Widget;

public class FormDialog extends Dialog {
	
	FormPanel		mFormPanel;
	Status 			mStatus;
	Button			mDefaultButton;
	
	public FormDialog(String title) {
		setModal(true);
		setHeading(title);
		setLayout(new FitLayout());
	    setButtonAlign(HorizontalAlignment.LEFT);
	    setButtons("");
	    
		mFormPanel = new FormPanel(); 
		mFormPanel.setHeaderVisible(false);
		mFormPanel.setFrame(true);  
		mFormPanel.setAutoWidth(true);
		add(mFormPanel);
		
/*
 * 		mFormPanel.addListener(Events.KeyDown, new Listener<ComponentEvent>(){
	    	public void handleEvent(ComponentEvent e) {
    		
	    		switch ( e.getKeyCode() ) {
	    			case KeyCodes.KEY_ESCAPE:
	    				endDialog();
	    				break;
	    			case KeyCodes.KEY_ENTER:
	    				if ( mDefaultButton != null)
	    					mDefaultButton.fireEvent(Events.Select);
	    				break;

	    		}
		}});
*/
		}
	
	protected void addField(Widget w) {
		mFormPanel.add(w, new FormData("-20"));
	}
	
	protected void setDefaultButton(Button button) {
		mDefaultButton = button;
	}
	
	protected void endDialog() {
		end();
		hide();
	}
	
	  @Override
	  protected void createButtons() {
	    super.createButtons();
	    
		mStatus = new Status();
		mStatus.setBusy("please wait...");
		mStatus.setAutoWidth(true);
		mStatus.hide();
	    getButtonBar().add(mStatus);

	    getButtonBar().add(new FillToolItem());
	  }
	  
	  protected void begin() {
		  mStatus.show();
		  getButtonBar().disable();
	  }
	  
	  protected void end() {
		  mStatus.hide();
		  getButtonBar().enable();
	  }	
}
