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
    static void setUserTagCloud() {
    	    	
    	User user = getConnectedUser();
		renderArgs.put("isLoggedIn", user != null);
		if (user != null) {
			
			Query queryOffer = JPA.em().createQuery("SELECT p.name, count(*) " 
					+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + user.id.toString() 
					+ " group by p.name");
			
			Query queryRequest = JPA.em().createQuery("SELECT p.name, count(*) " 
                    + "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + user.id.toString()
					+ " group by p.name");
         
			List<Object[]> listOffer = queryOffer.getResultList();
			List<Object[]> listRequest = queryRequest.getResultList();
			List<Object[]> list = new ArrayList<Object[]>(); 

	       	
	    	List<TagCloudItem> tagCloud = new ArrayList<TagCloudItem>(); 
	    	Integer listFlag = 0;
	    	
	    	for(Object[] a : listOffer)
	       	{
	    		boolean flag = false;
	    		Long addCount = (Long) a[1];
	    		int indexB = -1;
	    		
	    		if(listRequest != null)
	    		{	    			
		    		for(Object[] c : listRequest)
		    		{
		    			if(((String) a[0]).contains((String) c[0]))
		    			{
		    				addCount += (Long) c[1];	
		    				indexB = listRequest.indexOf(c);
		    			}
		    		}
		    		
		    		if(indexB > -1)
			    		listRequest.remove(indexB);			    		
	    		}
	    		
	    		Object[] newItem = {(String) a[0], addCount};
	    		list.add(newItem);
	       	}	    	
	    	
	    	for(Object[] b : listRequest)
	       	{
	    		Object[] newItem = {(String) b[0], (Long) b[1]};
	    		list.add(newItem);
	       	}
	    	
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
	       	    
	       	    t_Item.CssClass = "tag_" + cssFlag.toString();       	    		
	       		
	       		if(listFlag < 13)
	       			tagCloud.add(t_Item);
	       	}  
	       	
	    	Collections.shuffle(tagCloud);
	    	
	    	renderArgs.put("tagUserCloud", tagCloud); 			
		} 
    }
    
    @Before
    static void setOfferTagCloud() {
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where is_offer is not null or offer_id is not null group by name order by count(*) desc");
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
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where is_request is not null or request_id is not null group by name order by count(*) desc");
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
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where is_offer is not null or offer_id is not null group by name order by count(*) desc");
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
    	
    	Query query = JPA.em().createQuery("select name, count(*) from Tag where is_request is not null or request is not null group by name order by count(*) desc");
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
