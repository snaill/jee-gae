package com.jeebook.appengine.gtd.server.service;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.appengine.api.users.User;
import com.jeebook.appengine.gtd.server.model.Project;
import com.jeebook.appengine.gtd.server.model.ProjectValue;
import com.jeebook.appengine.gtd.server.persistence.JdoUtils;

public class ProjectService extends Service {

	@Override
	@SuppressWarnings("unchecked")
	public String get(String pathInfo) throws ServiceException {
		User user = getUser();
		String id = getId(pathInfo);

		if ( id.isEmpty() ) {
			PersistenceManager pm = JdoUtils.getPm();
			Query query = pm.newQuery(Project.class);
			query.setFilter("mUser == user");
		    query.declareParameters(user.getClass().getName() + " user");
			List<Project> projects = (List<Project>)query.execute(user);
			List<ProjectValue> values = Project.toValue(projects);
			return gson.toJson(values);
		}  else {
			PersistenceManager pm = JdoUtils.getPm();
			Project project = pm.getObjectById(Project.class, id);
			List<ProjectValue> values = new ArrayList<ProjectValue>();
			values.add(project.toValue());
			return gson.toJson(values);
		}
	}
	
	@Override
	public String create(String json) throws ServiceException { 
		User user = getUser();
		
		ProjectValue value = gson.fromJson(json, ProjectValue.class);
		Project project = Project.fromValue(user, value);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			project = pm.makePersistent(project);
		} finally {
			JdoUtils.closePm();
		}

		value = project.toValue();
		return gson.toJson(value);
	}

	@Override
	public String delete(String pathInfo) {
		String id = getId(pathInfo);

		PersistenceManager pm = JdoUtils.getPm();
		Project project = null;
		try {
			project = pm.getObjectById(Project.class, id);
			pm.deletePersistent(project);
		} finally {
			JdoUtils.closePm();
		}
		
		return gson.toJson(project.toValue());
	}
	
	@Override
	public String modify(String json) {
		ProjectValue value = gson.fromJson(json, ProjectValue.class);

		//
		PersistenceManager pm = JdoUtils.getPm();
		try {
			Project project = pm.getObjectById(Project.class, value.getId());
			project.setName(value.getName());
			return gson.toJson(project.toValue());
		} finally {
			JdoUtils.closePm();
		}
	}
}
