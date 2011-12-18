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
       	    t_Item.hyperlink = "";
       		
      	    Integer cssFlag = 0;
       	    
       	    if(listFlag%2 == 0)
       	    	cssFlag = (listFlag + 2)/2;
       	    else cssFlag = (listFlag + 1)/2;
       	    
       	    t_Item.CssClass = "tag_" + cssFlag.toString();       	    		
       		
       		if(listFlag < 13)
       			tagCloud.add(t_Item);
       	}  
       	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloud", tagCloud);  
    }
    
    @Before
    static void setOfferTagCloud() {
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where request_id is null group by name order by count(*) desc");
    	List<Object[]> list = query.getResultList();
       	
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
    	Integer listFlag = 0;
    	
    	for(Object[] a : list)
       	{
    		listFlag ++;
       		
       		TagCloudItem t_Item = new TagCloudItem();
       		t_Item.name = (String) a[0];
       		t_Item.count = (Long)a[1];
       	    t_Item.hyperlink = "";
       		
      	    Integer cssFlag = 0;
       	    
       	    if(listFlag%2 == 0)
       	    	cssFlag = (listFlag + 2)/2;
       	    else cssFlag = (listFlag + 1)/2;
       	    
       	    t_Item.CssClass = "tag_" + cssFlag.toString();       	    		
       		
       		if(listFlag < 13)
       			tagCloud.add(t_Item);
       	}  
       	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloudOffer", tagCloud);  
    }
    
    @Before
    static void setRequestTagCloud() {
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where offer_id is null group by name order by count(*) desc");
    	List<Object[]> list = query.getResultList();
       	
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
    	Integer listFlag = 0;
    	
    	for(Object[] a : list)
       	{
    		listFlag ++;
       		
       		TagCloudItem t_Item = new TagCloudItem();
       		t_Item.name = (String) a[0];
       		t_Item.count = (Long)a[1];
       	    t_Item.hyperlink = "";
       		
      	    Integer cssFlag = 0;
       	    
       	    if(listFlag%2 == 0)
       	    	cssFlag = (listFlag + 2)/2;
       	    else cssFlag = (listFlag + 1)/2;
       	    
       	    t_Item.CssClass = "tag_" + cssFlag.toString();       	    		
       		
       		if(listFlag < 13)
       			tagCloud.add(t_Item);
       	}  
       	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloudRequest", tagCloud);  
    }
    
    @Before
    static void setOfferTagCloudBig() {
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where request_id is null group by name order by count(*) desc");
    	List<Object[]> list = query.getResultList();
       	
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
    	Integer listFlag = 0;
    	
    	for(Object[] a : list)
       	{
    		listFlag ++;
       		
       		TagCloudItem t_Item = new TagCloudItem();
       		t_Item.name = (String) a[0];
       		t_Item.count = (Long)a[1];
       		t_Item.hyperlink = "/offers/search?phrase=" + t_Item.name;
       		
      	    Integer cssFlag = 0;
       	    
       	    if(listFlag%2 == 0)
       	    	cssFlag = (listFlag + 2)/2;
       	    else cssFlag = (listFlag + 1)/2;
       	    
       	    t_Item.CssClass = "tag_big_" + cssFlag.toString();       	    		
       		
       		if(listFlag < 13)
       			tagCloud.add(t_Item);
       	}  
       	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloudBigOffer", tagCloud);  
    }
    
    @Before
    static void setRequestTagCloudBig() {
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where offer_id is null group by name order by count(*) desc");
    	List<Object[]> list = query.getResultList();
       	
    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
    	Integer listFlag = 0;
    	
    	for(Object[] a : list)
       	{
    		listFlag ++;
       		
       		TagCloudItem t_Item = new TagCloudItem();
       		t_Item.name = (String) a[0];
       		t_Item.count = (Long)a[1];
       		t_Item.hyperlink = "/requests/search?phrase=" + t_Item.name;
       		
      	    Integer cssFlag = 0;
       	    
       	    if(listFlag%2 == 0)
       	    	cssFlag = (listFlag + 2)/2;
       	    else cssFlag = (listFlag + 1)/2;
       	    
       	    t_Item.CssClass = "tag_big_" + cssFlag.toString();       	    		
       		
       		if(listFlag < 13)
       			tagCloud.add(t_Item);
       	}  
       	
    	Collections.shuffle(tagCloud);
    	
    	renderArgs.put("tagCloudBigRequest", tagCloud);  
    }
    
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		}
		return null;
    }
}
