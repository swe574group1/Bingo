package controllers;

import java.util.List;
import models.*;
import play.mvc.*;
import play.data.validation.*;

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
	render(observedUser, isOwnProfile);
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
