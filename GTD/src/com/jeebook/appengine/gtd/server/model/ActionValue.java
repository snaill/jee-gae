package com.jeebook.appengine.gtd.server.model;

public class ActionValue {
    private String mId;
    
    private String mStatus;
    
    private String mName;

    private String mDetails;

    private String mProjectId;
    
    private String mContextId;
    
    private String mDueDate;

    private String mFinishDate;
    
    public final String getId() {
        return mId;
    }

    public final String getStatus() {
        return mStatus;
    }
    
	public final String getName() {
		return mName;
	}

	public final String getDetails() {
		return mDetails;
	}

	public final String getProjectId() {
		return mProjectId;
	}

	public final String getContextId() {
		return mContextId;
	}

	public final String getDueDate() {
		return mDueDate;
	}

	public final String getFinishDate() {
		return mFinishDate;
	}

    public final void setId( String id ) {
        mId = id;
    }

    public final void setStatus( String status ) {
        mStatus = status;
    }

	public final void setName( String name ) {
		mName = name;
	}

	public final void setDetails( String details ) {
		mDetails = details;
	}

	public final void setProjectId( String id ) {
		mProjectId = id;
	}

	public final void setContextId( String id ) {
		mContextId = id;
	}

	public final void setDueDate( String date ) {
		mDueDate = date;
	}

	public final void setFinishDate( String date ) {
		mFinishDate = date;
	}	
}
