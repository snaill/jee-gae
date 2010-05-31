package com.jeebook.appengine.gtd.server.model;

public class ProjectValue {

	private String mId;
    
    private String mName;
    
    private String mDefaultContextId;
    
    public final String getId() {
        return mId;
    }

    public final String getName() {
        return mName;
    }

    public final String getDefaultContextId() {
        return mDefaultContextId;
    }  
    
    public final void setId( String id ) {
        mId = id;
    }

    public final void setName( String name ) {
        mName = name;
    }

    public final void setDefaultContextId( String id ) {
        mDefaultContextId = id;
    }   
}
