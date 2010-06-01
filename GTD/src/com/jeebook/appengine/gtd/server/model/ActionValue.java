package com.jeebook.appengine.gtd.server.model;

public class ActionValue {
    private String id;
    
    private String status;
    
    private String name;

    private String details;

    private ProjectValue project;
    
    private ContextValue context;
    
    private String dueDate;

    private String finishDate;
    
    public final String getId() {
        return id;
    }

    public final String getStatus() {
        return status;
    }
    
	public final String getName() {
		return name;
	}

	public final String getDetails() {
		return details;
	}

	public final ProjectValue getProject() {
		return project;
	}

	public final ContextValue getContext() {
		return context;
	}

	public final String getDueDate() {
		return dueDate;
	}

	public final String getFinishDate() {
		return finishDate;
	}

    public final void setId( String i ) {
        id = i;
    }

    public final void setStatus( String s ) {
        status = s;
    }

	public final void setName( String n ) {
		name = n;
	}

	public final void setDetails( String d ) {
		details = d;
	}

	public final void setProject( ProjectValue p ) {
		project = p;
	}

	public final void setContext( ContextValue c ) {
		context = c;
	}

	public final void setDueDate( String date ) {
		dueDate = date;
	}

	public final void setFinishDate( String date ) {
		finishDate = date;
	}	
}
