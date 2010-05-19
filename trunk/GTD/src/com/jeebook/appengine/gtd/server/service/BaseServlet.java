package com.jeebook.appengine.gtd.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	String mType;

	@Override
	public void init() {
		mType = this.getInitParameter("type");
	}
	
	Service getService(String method) {
		if ( mType.equals( "action" ) )
			return new LoginService();
		else if ( mType.equals( "login" ) )
			return new LoginService();
		else if ( mType.equals( "context" ) )
			return new ContextService();
		else if ( mType.equals( "project" ) )
			return new ProjectService();	
		else if ( mType.equals( "inbox" ) )
			return new ActionListService(mType);
		else if ( mType.equals( "nextaction" ) )
			return new ActionListService(mType);
		else if ( mType.equals( "someday" ) )
			return new ActionListService(mType);
			
		return null;
	}
	
	 @Override
	protected  void	doGet(HttpServletRequest req, HttpServletResponse resp) 
	{
		 //
		 Service 	s = getService(req.getMethod());
		 if ( s == null )
			 return;
		 
		 String 	out;
		 try {
			out = s.get(req.getPathInfo());
			if ( null != out && !out.isEmpty() )
				Write(out, resp);
		} catch (ServiceException e) {
			sendError(resp, e);
		}
	}
	
	 @Override
	protected  void	doPost(HttpServletRequest req, HttpServletResponse resp) 
	{
		 //
		 Service 	s = getService(req.getMethod());
		 if ( s == null )
			 return;
		 
		 String 	out;
		 try {
			String	json = Read(req);
			out = s.create(json);
			if ( null != out && !out.isEmpty() )
				Write(out, resp);
		} catch (ServiceException e) {
			sendError(resp, e);
		}
	}
	
	 @Override
	protected  void	doDelete(HttpServletRequest req, HttpServletResponse resp) 
	{
		 //
		 Service 	s = getService(req.getMethod());
		 if ( s == null )
			 return;
		 
		 String 	out;
		 try {
			out = s.delete(req.getPathInfo());
			if ( null != out && !out.isEmpty() )
				Write(out, resp);
		} catch (ServiceException e) {
			sendError(resp, e);

		}
	}
	
	 @Override
	protected  void	doPut(HttpServletRequest req, HttpServletResponse resp) 
	{
		 //
		 Service 	s = getService(req.getMethod());
		 if ( s == null )
			 return;
		 
		 String 	out;
		 try {
			String	json = Read(req);
			out = s.modify(json);
			if ( null != out && !out.isEmpty() )
				Write(out, resp);
		} catch (ServiceException e) {
			sendError(resp, e);
		}
	}

	protected String Read(HttpServletRequest req)
	{
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)req.getInputStream()));
	        String line = null;
	        while((line = br.readLine())!=null){
	            sb.append(line);
	        }
		    return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected void Write(String jo, HttpServletResponse resp)
	{
        PrintWriter out;
		try {
			out = resp.getWriter();
	        out.write(jo);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void sendError(HttpServletResponse resp, ServiceException e) {
		resp.setStatus(e.getStatus());
		Write( e.getMessage(), resp );
	}
	
    protected User checkUser(HttpServletResponse resp) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
    	if ( null == user )
    	{
    		try {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, userService.createLoginURL("/Shuffle.html"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;
    	}
    	
    	return user;
    }
}
