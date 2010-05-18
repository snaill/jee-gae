package com.jeebook.appengine.gtd.server.service;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.jeebook.appengine.gtd.server.model.Action;
import com.jeebook.appengine.gtd.server.model.ActionValue;
import com.jeebook.appengine.gtd.server.persistence.JdoUtils;

@SuppressWarnings("serial")
public class ActionListService extends BaseServlet {

	String mType;

	@Override
	public void init() {
		mType = this.getInitParameter("type");
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		//
		User user = checkUser(resp);
		if (null == user)
			return;

		String json = "";
		if (mType == "nextAction") {

		} else {
			Integer status = Action.inboxStatus;
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
			query.declareParameters("Integer status");
			List<Action> actions = (List<Action>) query.execute(user, status);
			List<ActionValue> values = Action.toValue(actions);
			json = ActionValue.toJson(values);
		}
		
		Write(json, resp);
	}
}
