package controllers;

import java.util.*;

import models.*;
import play.data.validation.*;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.*;

public class BaseController extends Controller
{
    @Before
    static void setConnectedUser() {
    	User user = getConnectedUser();
		renderArgs.put("isLoggedIn", user != null);
		if (user != null) {
		    renderArgs.put("user", user);
		}
    }

    @Before
    static void setTagCloud() {
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>();
    	
    	
    	
    	TagCloudItem a = new TagCloudItem();
    	a.name = "Basketbol";
    	a.CssClass = "tag_1";
    	
    	tagCloud.add(a);
    	
    	TagCloudItem b = new TagCloudItem();
    	b.name = "Yüzme";
    	b.CssClass = "tag_2";
    	
    	tagCloud.add(b);
       	
    	renderArgs.put("tagCloud", tagCloud);  
    }
    
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		}
		return null;
    }
}
