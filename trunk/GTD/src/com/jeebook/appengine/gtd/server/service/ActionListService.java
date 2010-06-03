package com.jeebook.appengine.gtd.server.service;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.jeebook.appengine.gtd.server.model.Action;
import com.jeebook.appengine.gtd.server.model.ActionValue;
import com.jeebook.appengine.gtd.server.persistence.JdoUtils;

public class ActionListService extends Service {

	String mType;

	public ActionListService( String type ) {
		mType = type;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String get(String pathInfo) throws ServiceException { 
		User user = getUser();
		
		String json = "";
		if (mType == "nextAction") {
			json = "";
		} else {
			String status = Action.inboxStatus;
			if (mType == "waiting")
				status = Action.waitingStatus;
			else if (mType == "someday")
				status = Action.somedayStatus;
			else if (mType == "finish")
				status = Action.finishStatus;

			//
			PersistenceManager pm = JdoUtils.getPm();
			Query query = pm.newQuery(Action.class);
			query.setFilter("mUser == user && mStatus == status");
			query.declareParameters(user.getClass().getName() + " user");
			query.declareParameters("String status");
			List<Action> actions = (List<Action>) query.execute(user, status);
			List<ActionValue> values = Action.toValue(actions);
			json = gson.toJson(values, ActionValue.class);
		}
		return json;
	}
}
