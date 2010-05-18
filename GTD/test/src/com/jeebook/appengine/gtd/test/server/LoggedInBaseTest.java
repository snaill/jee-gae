package com.jeebook.appengine.gtd.test.server;


import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

import com.google.appengine.tools.development.testing.*;

public class LoggedInBaseTest extends TestCase {

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(), 
        						   new LocalUserServiceTestConfig(),
        						   new LocalURLFetchServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
        helper.setEnvEmail("test@gmail.com");
        helper.setEnvIsLoggedIn(true);
        helper.setEnvAuthDomain("gmail.com");
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    protected LocalServiceTestHelper getHelper() { return helper; }
    protected String sendRequest(String url, String method, String json ) {
    	try {
    		String realUrl = getRealUrl(url);
    		HttpURLConnection conn = (HttpURLConnection)new URL(realUrl).openConnection();
    		if ( !json.isEmpty() ) {
    			conn.setDoOutput(true);
	    		OutputStream os = conn.getOutputStream(); 
	    		os.write(json.getBytes("utf-8"));
	    		os.flush();
	    		os.close();
    		}
    		
    		InputStream is = conn.getInputStream();
    		int count = is.available();
    		byte[] ba = new byte[count];
    		return new String(ba, Charset.forName("utf-8"));
    	} catch ( Exception e ) {
    		Assert.fail();
    	}
    	
    	return "";
    }
    
    protected String getRealUrl( String api ) {
    	return "http://127.0.0.1:8888/shuffle/" + api + "?gwt.codesvr=127.0.0.1:9997";
    }
}
