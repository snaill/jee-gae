package com.jeebook.appengine.gtd.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.appengine.api.users.User;
import com.jeebook.appengine.gtd.server.model.Action;
import com.jeebook.appengine.gtd.server.model.ActionValue;
import com.jeebook.appengine.gtd.server.persistence.JdoUtils;

public class ActionService extends Service {

	@Override
	@SuppressWarnings("unchecked")
	public String get(String pathInfo) throws ServiceException {
		User user = getUser();
		String id = getId(pathInfo);

		if ( id.isEmpty() ) {
			PersistenceManager pm = JdoUtils.getPm();
			Query query = pm.newQuery(Action.class);
			query.setFilter("mUser == user");
		    query.declareParameters(user.getClass().getName() + " user");
			List<Action> actions = (List<Action>)query.execute(user);
			List<ActionValue> values = Action.toValue(actions);
			return gson.toJson(values);
		}  else {
			PersistenceManager pm = JdoUtils.getPm();
			Action action = pm.getObjectById(Action.class, id);
			List<ActionValue> values = new ArrayList<ActionValue>();
			values.add(action.toValue());
			return gson.toJson(values);
		}
	}
	
	@Override
	public String create(String json) throws ServiceException { 
		User user = getUser();
		
		ActionValue value = gson.fromJson(json, ActionValue.class);
		Action action = Action.fromValue(user, value);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			action = pm.makePersistent(action);
		} finally {
			JdoUtils.closePm();
		}

		value = action.toValue();
		return gson.toJson(value);
	}

	@Override
	public String delete(String pathInfo) {
		String id = getId(pathInfo);

		PersistenceManager pm = JdoUtils.getPm();
		Action action = null;
		try {
			action = pm.getObjectById(Action.class, id);
			pm.deletePersistent(action);
		} finally {
			JdoUtils.closePm();
		}
		
		return gson.toJson(action.toValue());
	}
	
	@Override
	public String modify(String json) {
		ActionValue value = gson.fromJson(json, ActionValue.class);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			Action action = pm.getObjectById(Action.class, value.getId());
			action.setName(value.getName());
			return gson.toJson(action.toValue());
		} finally {
			JdoUtils.closePm();
		}
	}
}
