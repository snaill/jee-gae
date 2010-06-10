package com.jeebook.appengine.gtd.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.users.User;
import com.jeebook.appengine.gtd.server.persistence.JdoUtils;

@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Unique(members={"mId", "mName"})
public class Action implements Serializable {

	public static final String inboxStatus = "inbox";
	public static final String waitingStatus = "waiting";
	public static final String somedayStatus = "someday";
	public static final String finishStatus = "finish";
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long mId;
    
    @Persistent
    private User mUser;
    
    @Persistent
    private String mStatus;
    
    @Persistent
    private String mName;

    @Persistent
    private String mDetails;

    @Persistent
    private Long mProjectId;
    
    @Persistent
    private Long mContextId;
    
    @Persistent
    private String mDueDate;

    @Persistent
    private String mFinishDate;
    
    public final Long getId() {
        return mId;
    }

    public final String getStatus() {
    	if ( null == mStatus )
    		return inboxStatus;
        return mStatus;
    }
    
    public final User getUser() {
        return mUser;
    }
    
	public final String getName() {
		return mName;
	}

	public final String getDetails() {
		return mDetails;
	}

	public final Long getProjectId() {
		return mProjectId;
	}

	public final Long getContextId() {
		return mContextId;
	}

	public final String getDueDate() {
		return mDueDate;
	}

	public final String getFinishDate() {
		return mFinishDate;
	}

    public final void setId( Long id ) {
        mId = id;
    }

    public final void setStatus( String status ) {
        mStatus = status;
    }
    
    public final void setUser( User user ) {
        mUser = user;
    }
    
	public final void setName( String name ) {
		mName = name;
	}

	public final void setDetails( String details ) {
		mDetails = details;
	}

	public final void setProjectId( Long id ) {
		mProjectId = id;
	}

	public final void setContextId( Long id ) {
		mContextId = id;
	}

	public final void setDueDate( String date ) {
		mDueDate = date;
	}

	public final void setFinishDate( String date ) {
		mFinishDate = date;
	}
	
	public static Action fromValue( User user, ActionValue value ) {
		Action action = new Action();
		if ( null != value.getId() )
			action.setId(Long.parseLong(value.getId()));
		if ( null != value.getStatus() )
			action.setStatus(value.getStatus());
		action.setName(value.getName());
		action.setUser(user);
		action.setDetails(value.getDetails());
		if ( null != value.getProject() )
			action.setProjectId(Long.parseLong(value.getProject().getId()));
		if ( null != value.getContext() )
			action.setContextId(Long.parseLong(value.getContext().getId()));
		action.setDueDate(value.getDueDate());
		action.setFinishDate(value.getFinishDate());
		return action;
	}
	
	public static List<ActionValue> toValue( List<Action> actions ) {
        List<ActionValue> values = new ArrayList<ActionValue>();
        for ( int i = 0; i < actions.size(); i ++ ) {
        	values.add(actions.get(i).toValue());
        }
        return values;
	}
	
	public ActionValue toValue() {
		ActionValue value = new ActionValue();
		value.setId(getId().toString());
		value.setStatus(getStatus().toString());
		value.setName(getName());
		value.setDetails(getDetails());
		if ( null != getProjectId() ) {
			PersistenceManager pm = JdoUtils.getPm();
			Project project = pm.getObjectById(Project.class, getProjectId().toString());
			value.setProject(project.toValue());	
		}
		if ( null != getContextId() ) {
			PersistenceManager pm = JdoUtils.getPm();
			Context context = pm.getObjectById(Context.class, getContextId().toString());
			value.setContext(context.toValue());	
		}
		value.setDueDate(getDueDate());
		value.setFinishDate(getFinishDate());
		return value;
	}
}
