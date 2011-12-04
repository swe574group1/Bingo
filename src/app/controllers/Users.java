package controllers;

import java.util.List;
import models.*;
import play.mvc.*;
import play.data.validation.*;

public class Users extends BaseController
{

    public static void register() {
	render(new User());
    }

    public static void doCreateUserEntry(@Valid User user) {
	if (validation.hasErrors()) {
	    params.flash();
	    validation.keep();
	    register();
	}
	user.save();
	displayUserInfo(user);
    }

    public static void displayUserInfo(User user) {
	render(user);
    }

    public static void finalizeUser() {
	render();
    }

    public static void profile(Long userId) {
	User user = User.findById(userId);
	User connectedUser = getConnectedUser();
	Boolean isOwnProfile = (user == connectedUser);
	render(user, isOwnProfile);
    }

    public static void ownProfile() {
	User currentUser = getConnectedUser();
	profile(currentUser.id);
    }

    public static void editProfile() {
	User connectedUser = getConnectedUser();
	showEditableProfile(connectedUser.id);
    }

    public static void showEditableProfile(Long userId) {
	User user = User.findById(userId);
	render(user);
    }
}
