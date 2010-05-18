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
	public String get(String id) throws ServiceException {
		User user = getUser();
       
		if ( id.isEmpty() ) {
			PersistenceManager pm = JdoUtils.getPm();
			Query query = pm.newQuery(Action.class);
			query.setFilter("mUser == user");
		    query.declareParameters(user.getClass().getName() + " user");
			List<Action> actions = (List<Action>)query.execute(user);
			List<ActionValue> values = Action.toValue(actions);
			return ActionValue.toJson(values);
		}  else {
			PersistenceManager pm = JdoUtils.getPm();
			Action action = pm.getObjectById(Action.class, id);
			List<ActionValue> values = new ArrayList<ActionValue>();
			values.add(action.toValue());
			return ActionValue.toJson(values);
		}
	}
	
	@Override
	public String create(String json) throws ServiceException { 
		User user = getUser();
		
		ActionValue value = ActionValue.fromJson(json);
		Action action = Action.fromValue(user, value);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			action = pm.makePersistent(action);
		} finally {
			JdoUtils.closePm();
		}

		value = action.toValue();
		return value.toJson();
	}

	@Override
	public String delete(String id) {
		PersistenceManager pm = JdoUtils.getPm();
		Action action = null;
		try {
			action = pm.getObjectById(Action.class, id);
			pm.deletePersistent(action);
		} finally {
			JdoUtils.closePm();
		}
		
		return action.toValue().toJson();
	}
	
	@Override
	public String modify(String json) {
		ActionValue value = ActionValue.fromJson(json);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			Action action = pm.getObjectById(Action.class, value.getId());
			action.setName(value.getName());
			return action.toValue().toJson();
		} finally {
			JdoUtils.closePm();
		}
	}
}
