package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import models.BadgeType;
import models.Request;
import models.User;
import play.db.jpa.JPA;


public class BadgeManager {
	
	//BadgeType badge = null;
	// JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
	BadgeType	 getbadgelist(){
	User currentUser = getConnectedUser();
	Query getBadgeQuery = JPA.em().createQuery("select badge from   " + User.class.getName() + " where id is " +currentUser.id);
	BadgeType badge = (BadgeType) getBadgeQuery. getSingleResult();
//	Iterator<BadgeType> itr = badgeList.iterator();
//    while (itr.hasNext()) {
//      BadgeType element = itr.next();
//      System.out.print(element.type + " ");
//    }


   return badge;

	}
    static User getConnectedUser() {
		if (Security.isConnected()) {
		    return User.find("byEmail", Security.connected()).first();
		   
		}
		return null;
    }

	

	
}

