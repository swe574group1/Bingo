package controllers;

import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.*;
import play.mvc.*;
import play.data.validation.*;

import play.db.jpa.JPA;

public class Users extends BaseController
{

    public static void register() {
        User user = new User();
        
        renderTemplate("Users/form.html", user);
    }

    public static void save(@Valid User user) {
        boolean isCreate = (user.id == null);

    	if (validation.hasErrors()) {
    	    params.flash();
    	    validation.keep();
    	    register();
    	}
    	
    	if(isCreate)
    		user.badge=BadgeType.NEW_BEE;
    	
    	user.save();

    	if (isCreate) {
    	    success(user.id);
    	} else {
    	    ownProfile();
    	}
    }

    public static void success(Long userId) {
        User user = User.findById(userId);
        render(user);
    }

    public static void profile(Long userId) {
	User observedUser = User.findById(userId);
	User connectedUser = getConnectedUser();
	Boolean isOwnProfile = (observedUser == connectedUser);

	Query participatedHandshakes = JPA.em().createQuery("from " + Handshake.class.getName() + " where offererId=" + userId + " or requesterId=" + userId);
	List<Object[]> relevantHandshakeList = participatedHandshakes.getResultList();
	List<Handshake> handshakes = new ArrayList(relevantHandshakeList);

	Query openOffers = JPA.em().createQuery("from " + Offer.class.getName() + " where status='WAITING' and user_Id=" + userId);
	List<Object[]> offerList = openOffers.getResultList();
	List<Offer> offers = new ArrayList(offerList);

	Query openRequests = JPA.em().createQuery("from " + Request.class.getName() + " where status='WAITING' and user_Id=" + userId);
	List<Object[]> requestList = openRequests.getResultList();
	List<Request> requests = new ArrayList(requestList);

		/*Tagcloud start*/
		Query queryOffer = JPA.em().createQuery("SELECT p.name, count(*) " 
				+ "FROM Tag p JOIN p.offer e JOIN e.user u where u.id = " + userId
				+ " group by p.name");
		
		Query queryRequest = JPA.em().createQuery("SELECT p.name, count(*) " 
	            + "FROM Tag p JOIN p.request e JOIN e.user u where u.id = " + userId
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
		/*Tagcloud end*/
	
	render(observedUser, isOwnProfile, handshakes, offers, requests);
    }

    public static void ownProfile() {
	User currentUser = getConnectedUser();
	profile(currentUser.id);
    }

    public static void editProfile() {
        User connectedUser = getConnectedUser();
        User user = User.findById(connectedUser.id);
        renderTemplate("Users/form.html", user);
    }
}
