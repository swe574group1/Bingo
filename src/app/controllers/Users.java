package controllers;

import javax.persistence.Query;

import java.util.ArrayList;
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
	render(observedUser, isOwnProfile, handshakes);
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
