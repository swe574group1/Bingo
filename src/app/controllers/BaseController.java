package controllers;

import java.util.*;

import javax.persistence.Query;

import models.*;
import play.data.validation.*;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.*;
import play.db.jpa.JPA;

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
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag group by name order by count(*) desc");
    	List<Object[]> list = query.getResultList();
       	
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
    	Integer listFlag = 0;
    	
    	for(Object[] a : list)
       	{
    		listFlag ++;
       		
       		TagCloudItem t_Item = new TagCloudItem();
       		t_Item.name = (String) a[0];
       		t_Item.count = (Long)a[1];
       		
       		switch(listFlag)
       		{
       			case 1 : t_Item.CssClass = "tag_1"; break;
       			case 2 : t_Item.CssClass = "tag_2"; break;
       			case 3 : t_Item.CssClass = "tag_2"; break;
       			case 4 : t_Item.CssClass = "tag_3"; break;
       			case 5 : t_Item.CssClass = "tag_3"; break;       			
       			case 6 : t_Item.CssClass = "tag_4"; break;
       			case 7 : t_Item.CssClass = "tag_4"; break;
       			case 8 : t_Item.CssClass = "tag_5"; break;
       			case 9 : t_Item.CssClass = "tag_5"; break;
       			default : t_Item.CssClass = "tag_5"; 
       		}       	
       		
       		if(listFlag < 10)
       			tagCloud.add(t_Item);
       	}     	
    	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloud", tagCloud);  
    }
    
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		}
		return null;
    }
}
