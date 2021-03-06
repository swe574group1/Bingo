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
    static void set_New_Bee() {
    	Query query = JPA.em().createQuery("select id, fullname, registrationDate, photo, address from User where badge = 'NEW_BEE'");
    	List<Object[]> list = query.getResultList();
    	List<User> users_new_bee_base = new ArrayList<User>();
    	List<User> users_new_bee = new ArrayList<User>();
    	Integer count = 0;
    	
    	for(Object[] a : list)
       	{       		
    		User usr = new User();
    		usr.id = (Long) a[0];
    		usr.fullname = (String) a[1];
    		usr.registrationDate = (Date) a[2];
    		usr.photo = (play.db.jpa.Blob ) a[3];
    		usr.address = (String ) a[4];
    		usr.balance = (double) 0;
       		
    		users_new_bee_base.add(usr);
       	}  
    	
    	Collections.shuffle(users_new_bee_base);
    	
    	for(User b : users_new_bee_base)
    	{
    		count ++;
    		/*
    		Query queryOffer = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    				*/
    		List<Object[]> listOffer = new ArrayList<Object[]>(); //queryOffer.getResultList();
        	/*
    		Query queryRequest = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    				*/
    		List<Object[]> listRequest = new ArrayList<Object[]>();//queryRequest.getResultList();
        	
        	b.balance = (double) (listOffer.size() + listRequest.size());
    		
   			if(count < 6)
   				users_new_bee.add(b);   				
    	}
    	
    	renderArgs.put("users_new_bee", users_new_bee); 
    }
    
    @Before
    static void set_Busy_Bee() {
    	Query query = JPA.em().createQuery("select id, fullname, registrationDate, photo, address from User where badge = 'BUSY_BEE'");
    	List<Object[]> list = query.getResultList();
    	List<User> users_new_bee_base = new ArrayList<User>();
    	List<User> users_new_bee = new ArrayList<User>();
    	Integer count = 0;
    	
    	for(Object[] a : list)
       	{       		
    		User usr = new User();
    		usr.id = (Long) a[0];
    		usr.fullname = (String) a[1];
    		usr.registrationDate = (Date) a[2];
    		usr.photo = (play.db.jpa.Blob ) a[3];
    		usr.address = (String ) a[4];
    		usr.balance = (double) 0;
       		
    		users_new_bee_base.add(usr);
       	}  
    	
    	Collections.shuffle(users_new_bee_base);
    	
    	for(User b : users_new_bee_base)
    	{
    		count ++;
    		
    		Query queryOffer = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listOffer = queryOffer.getResultList();
        	
    		Query queryRequest = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listRequest = queryRequest.getResultList();
        	
        	b.balance = (double) (listOffer.size() + listRequest.size());
    		
   			if(count < 6)
   				users_new_bee.add(b);   				
    	}
    	
    	renderArgs.put("users_busy_bee", users_new_bee); 
    }
    
    @Before
    static void set_Working_Bee() {
    	Query query = JPA.em().createQuery("select id, fullname, registrationDate, photo, address from User where badge = 'WORKING_BEE'");
    	List<Object[]> list = query.getResultList();
    	List<User> users_new_bee_base = new ArrayList<User>();
    	List<User> users_new_bee = new ArrayList<User>();
    	Integer count = 0;
    	
    	for(Object[] a : list)
       	{       		
    		User usr = new User();
    		usr.id = (Long) a[0];
    		usr.fullname = (String) a[1];
    		usr.registrationDate = (Date) a[2];
    		usr.photo = (play.db.jpa.Blob ) a[3];
    		usr.address = (String ) a[4];
    		usr.balance = (double) 0;
       		
    		users_new_bee_base.add(usr);
       	}  
    	
    	Collections.shuffle(users_new_bee_base);
    	
    	for(User b : users_new_bee_base)
    	{
    		count ++;
    		
    		Query queryOffer = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listOffer = queryOffer.getResultList();
        	
    		Query queryRequest = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listRequest = queryRequest.getResultList();
        	
        	b.balance = (double) (listOffer.size() + listRequest.size());
    		
   			if(count < 6)
   				users_new_bee.add(b);   				
    	}
    	
    	renderArgs.put("users_working_bee", users_new_bee); 
    }
    
    @Before
    static void set_Bumble_Bee() {
    	Query query = JPA.em().createQuery("select id, fullname, registrationDate, photo, address from User where badge = 'BUMBLE_BEE'");
    	List<Object[]> list = query.getResultList();
    	List<User> users_new_bee_base = new ArrayList<User>();
    	List<User> users_new_bee = new ArrayList<User>();
    	Integer count = 0;
    	
    	for(Object[] a : list)
       	{       		
    		User usr = new User();
    		usr.id = (Long) a[0];
    		usr.fullname = (String) a[1];
    		usr.registrationDate = (Date) a[2];
    		usr.photo = (play.db.jpa.Blob ) a[3];
    		usr.address = (String ) a[4];
    		usr.balance = (double) 0;
       		
    		users_new_bee_base.add(usr);
       	}  
    	
    	Collections.shuffle(users_new_bee_base);
    	
    	for(User b : users_new_bee_base)
    	{
    		count ++;
    		
    		Query queryOffer = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listOffer = queryOffer.getResultList();
        	
    		Query queryRequest = JPA.em().createQuery("SELECT e.id " 
    				+ "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + b.id.toString()
    				+ " group by e.id");
    		List<Object[]> listRequest = queryRequest.getResultList();
        	
        	b.balance = (double) (listOffer.size() + listRequest.size());
    		
   			if(count < 6)
   				users_new_bee.add(b);   				
    	}
    	
    	renderArgs.put("users_bumble_bee", users_new_bee); 
    }
    
    
    @Before
    static void setOfferTagCloud() {
    	
    	//Query query = JPA.em().createQuery("select name, count(*) from Tag where offer_id is not null group by name order by count(*) desc");
    	List<Object[]> list = new ArrayList<Object[]>();//query.getResultList();
       	
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
    	
    	//Query query = JPA.em().createQuery("select name, count(*) from Tag where request_id is not null group by name order by count(*) desc");
    	List<Object[]> list = new ArrayList<Object[]>(); //query.getResultList();
       	
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
    	
    	//Query query = JPA.em().createQuery("select name, count(*) from Tag where offer_id is not null group by name order by count(*) desc");
    	List<Object[]> list = new ArrayList<Object[]>(); //query.getResultList();
       	
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
    	
    	//Query query = JPA.em().createQuery("select name, count(*) from Tag where request_id is not null group by name order by count(*) desc");
    	List<Object[]> list = new ArrayList<Object[]>(); //query.getResultList();
       	
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
    
    
    @Before
    static void setDistricts() {
    	
    	Query query = JPA.em().createQuery("select name,id,county.id from District order by county_id, name");
    	List<Object[]> list = query.getResultList();
       	
    	List<DistrictItem> districts = new ArrayList<DistrictItem>(); 
    	
    	for(Object[] a : list)
       	{    		       		
    		DistrictItem t_Item = new DistrictItem();
       		t_Item.name = (String) a[0];
       		t_Item.district_id = (Long)a[1];
       		t_Item.county_id = (Long)a[2];
       		
       		districts.add(t_Item);
       	}  
       	    	
    	renderArgs.put("districts", districts);  
    }       
    
    @Before
    static void setCounties() {
    	
    	Query query = JPA.em().createQuery("select name,id,city.id from County order by city_id, name");
    	List<Object[]> list = query.getResultList();
       	
    	List<CountyItem> counties = new ArrayList<CountyItem>(); 
    	
    	CountyItem t_Item_All = new CountyItem();
    	t_Item_All.name = "All";
    	t_Item_All.county_id = Long.getLong("0");
    	t_Item_All.city_id = Long.getLong("0");
   		
   		counties.add(t_Item_All);
    	
    	for(Object[] a : list)
       	{    		       		
    		CountyItem t_Item = new CountyItem();
       		t_Item.name = (String) a[0];
       		t_Item.county_id = (Long)a[1];
       		t_Item.city_id = (Long)a[2];
       		
       		counties.add(t_Item);
       	}  
       	    	
    	renderArgs.put("counties", counties);  
    }       
    
    @Before
    static void setRecoccuranceHour() {
    	
    	Query query = JPA.em().createQuery("select hour_min,val from Recoccurancehour order by val");
    	List<Object[]> list = query.getResultList();
      
    	List<Recoccurancehouritem> hours = new ArrayList<Recoccurancehouritem>(); 
    	
    	for(Object[] a : list)
       	{    		       		
    		Recoccurancehouritem t_Item = new Recoccurancehouritem();
       		t_Item.hour_min = (String) a[0];
       		t_Item.val = (Long)a[1];
       		
       		hours.add(t_Item);
       	}  
       	    	
    	renderArgs.put("recoccurancehouritems", hours);  
    }      
    
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		}
		return null;
    }
}
