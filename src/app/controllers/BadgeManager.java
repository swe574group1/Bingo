package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import models.BadgeEntity;
import models.BadgeImage;
import models.BadgeType;
import models.Request;
import models.User;
import play.db.jpa.JPA;


public class BadgeManager {
	
	   //...................................

	
	BadgeType	 getbadgelist(){
	User currentUser = getConnectedUser();
	Query getBadgeQuery = JPA.em().createQuery("select badge from   " + User.class.getName() + " where id is " +currentUser.id);
	BadgeType badge = (BadgeType) getBadgeQuery. getSingleResult();

   return badge;

	}
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		   
		}
		return null;
    }
    //...................................

    
   String getUserEmail(){
	   User currentUser = getConnectedUser();
		User user = User.findById(currentUser.id);
		
		return user.email;
		
   }
   User getUserFromUserEntity(){
	   User currentUser = getConnectedUser();
		User user = User.findById(currentUser.id);
		
		return user;
		
   }
   //...................................

   public  BadgeEntity getBadgeEntity()  
   {   BadgeEntity badgeEntity;
   User user=getUserFromUserEntity();

       badgeEntity=  BadgeEntity.getEmailOfBadger(user.email);    
   	 if (badgeEntity==null) {  //singleton check
   		 BadgeEntity  newbadgeEntity = null;
   		 newbadgeEntity=new BadgeEntity(); 
   		newbadgeEntity.setEmail(user.email);
   		newbadgeEntity.save();
   		   	return 	 newbadgeEntity;
   	 }
   	 else{
   	     		 
   	 return  badgeEntity;
   		 
   	 }
   	
   }
   //...................................

   Long getUserId(){
	   User currentUser = getConnectedUser();
		User user = User.findById(currentUser.id);
				return user.id;
		  }
   
   //...................................
   
   public BadgeImage badgeCheck(BadgeImage image){

		BadgeEntity badgeEntity =getBadgeEntity() ;
		String fivestarresult=badgeEntity.getFivester();
		if(fivestarresult!=null){
			image.setFivester();
				}
		String GuruResult=badgeEntity.getGuru();
		if(GuruResult!=null){
			image.setGuru();
					}
		return image;
		   	   
   }

}

