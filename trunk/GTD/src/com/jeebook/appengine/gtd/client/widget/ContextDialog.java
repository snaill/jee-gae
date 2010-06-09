package com.jeebook.appengine.gtd.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.jeebook.appengine.gtd.client.model.ActionData;
import com.jeebook.appengine.gtd.client.model.ContextData;
import com.jeebook.appengine.gtd.client.model.ProjectData;

public class ContextDialog extends Dialog {
	
	public ContextDialog(ContextData pd) {
		setHeading("Context");
		setModal(true);
		
	    FormPanel simple = new FormPanel(); 
	    simple.setHeaderVisible(false);
	    simple.setFrame(true);  
	    simple.setAutoWidth(true);

	    TextField<String> firstName = new TextField<String>();  
	    firstName.setFieldLabel("Name");  
	    firstName.setAllowBlank(false);  
	    firstName.setData("text", "Enter your fist name");  
	    simple.add(firstName, new FormData("-20"));  
	      
	    add(simple);
	}
	
	  @Override
	  protected void createButtons() {
	    super.createButtons();
	    
	    Status status = new Status();
	    status.setBusy("please wait...");
	    status.hide();
	    status.setAutoWidth(true);
	    getButtonBar().add(status);
	    
	    getButtonBar().add(new FillToolItem());
	    
	    Button save = new Button("Save");
	    save.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
//	        userName.reset();
//	        password.reset();
//	        validate();
//	        userName.focus();
	      }

	    });

	    Button close = new Button("Close");
	    close.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
//	        onSubmit();
	      }
	    });

	    addButton(save);
	    addButton(close);

	  }
}
