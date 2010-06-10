package com.jeebook.appengine.gtd.client.widget;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.jeebook.appengine.gtd.client.model.ActionData;
import com.jeebook.appengine.gtd.client.model.ContextData;
import com.jeebook.appengine.gtd.client.model.ProjectData;
import com.jeebook.appengine.gtd.client.service.AjaxRequest;

public class ActionDialog extends FormDialog {
	
	 TextField<String> mNameTextField;
	 ComboBox<ModelData> mProjectComboBox;
	 ComboBox<ModelData> mContextComboBox;
	 DateField mDueDateField;
	 TimeField mDueTimeField;	 
	 TextArea mDetailsTextArea;
		Button			  mSaveButton;
		Button			  mCloseButton;	
		
	public ActionDialog(ActionData ad) {
		super("Action");
		setWidth(400);
		setHeight(300);	
 
	    // Name
	    mNameTextField = new TextField<String>();  
	    mNameTextField.setFieldLabel("Name");  
	    mNameTextField.setAllowBlank(false); 
	    mNameTextField.addKeyListener(new KeyListener(){

	    	public void handleEvent(ComponentEvent e) {
	    		super.handleEvent(e);

	    		if ( mNameTextField.getValue() != null ) {
	    			if ( !mSaveButton.isEnabled() )
	    				mSaveButton.enable();
	    		} else {
	    			if ( mSaveButton.isEnabled() )
	    				mSaveButton.disable();
	    		}	
	    	}
	    });
	    mNameTextField.focus();
	    addField(mNameTextField);  
	  
	    // Project
	    mProjectComboBox = new ComboBox<ModelData>();  
	    mProjectComboBox.setFieldLabel("Project");
	    mProjectComboBox.setStore(new ListStore<ModelData>());  
	    mProjectComboBox.setDisplayField("name");  
	    mProjectComboBox.setTypeAhead(true);  
	    mProjectComboBox.setTriggerAction(TriggerAction.ALL);
	    updateProjectData();
	    addField(mProjectComboBox); 
	    
	    // Context
	    mContextComboBox = new ComboBox<ModelData>();  
	    mContextComboBox.setStore(new ListStore<ModelData>());  
	    mContextComboBox.setDisplayField("name");  
	    mContextComboBox.setTypeAhead(true);  
	    mContextComboBox.setTriggerAction(TriggerAction.ALL);  
	    mContextComboBox.setFieldLabel("Context");
	    updateContextData();
	    addField(mContextComboBox); 
	    
	    // Due Date
	    mDueDateField = new DateField();  
	    mDueDateField.setFieldLabel("Due Date");  
	    addField(mDueDateField);  
	  
	    // Due Time
	    mDueTimeField = new TimeField();  
	    mDueTimeField.setFieldLabel("Due Time");  
	    addField(mDueTimeField);  
	    
	    // Details
	    mDetailsTextArea = new TextArea();  
	    mDetailsTextArea.setPreventScrollbars(true);  
	    mDetailsTextArea.setFieldLabel("Details");  
	    addField(mDetailsTextArea); 
	}
	
	  @Override
	  protected void createButtons() {
	    super.createButtons();
	    
	    Button btn = new Button("Project +");
	    btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
	    	  ProjectDialog dlg = new ProjectDialog(null);
	    	  dlg.addListener(Events.Hide, new Listener<WindowEvent>() {
    	        public void handleEvent(WindowEvent be) {
    	        	updateProjectData();
    	        }
    	      });
	    	  dlg.show();
	      }

	    });
	    addButton(btn);

	    Button btn2 = new Button("Context +");
	    btn2.addSelectionListener(new SelectionListener<ButtonEvent>() {
	      public void componentSelected(ButtonEvent ce) {
	    	  ContextDialog dlg = new ContextDialog(null);
	    	  dlg.addListener(Events.Hide, new Listener<WindowEvent>() {
	    	        public void handleEvent(WindowEvent be) {
	    	        	updateContextData();
	    	        }
	    	  });
	    	  dlg.show();
	      }

	    });
	    addButton(btn2);

	    //    
		    mSaveButton = new Button("Save");
		    mSaveButton.disable();
		    mSaveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
		      public void componentSelected(ButtonEvent ce) {
		    	  begin();
			  		ActionData ad = (ActionData)ActionData.createObject();
					ad.setName(mNameTextField.getValue());
					ad.setDetails(mDetailsTextArea.getValue());
//					if ( projectListBox.getSelectedIndex() >= 0 )
//						ad.setProjectId(projectListBox.getValue(projectListBox.getSelectedIndex()));
//					ad.setContextId(contextListBox.getValue(contextListBox.getSelectedIndex()));
//					Date dueTime = dueTimeDatePicker.getValue();
//					if ( null != dueTime )
//						ad.setDueDate(dueTime.toString());
					
					new AjaxRequest(null, RequestBuilder.POST, "action/"){
						
						@Override
						public void onSuccess(Object param, String response){
							reset();
						}
					}.send(new JSONObject(ad).toString());	
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
	  
	  protected void updateProjectData() {
		  mProjectComboBox.getStore().removeAll();
			new AjaxRequest(mProjectComboBox, RequestBuilder.GET, "project/") {
				
				@Override
				public void onSuccess(Object param, String response){
					JSONArray ja = (JSONArray)JSONParser.parse(response);
					ListStore<ModelData> ls = mProjectComboBox.getStore();
					for ( int i = 0; i < ja.size(); i ++ ) {
						ProjectData pd = (ProjectData)ja.get(i).isObject().getJavaScriptObject();
						ls.add(pd.toModelData());
					}
				}
			}.send(null);	
	  }
	  
	  protected void updateContextData() {
		  mContextComboBox.getStore().removeAll();
			new AjaxRequest(mContextComboBox, RequestBuilder.GET, "context/") {
				
				@Override
				public void onSuccess(Object param, String response){
					JSONArray ja = (JSONArray)JSONParser.parse(response);
					ListStore<ModelData> ls = mContextComboBox.getStore();
					for ( int i = 0; i < ja.size(); i ++ ) {
						ContextData pd = (ContextData)ja.get(i).isObject().getJavaScriptObject();
						ls.add(pd.toModelData());
					}
				}
			}.send(null);	
	  }
}
