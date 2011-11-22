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

    public static void listOffers(User user) {
	List<Offer> offers = Offer.find("byUserEmail", user.email).fetch();
	render(user, offers);
    }

    public static void listRequests(User user) {
	List<Request> requests = Request.find("byUserEmail", user.email).fetch();
	render(user, requests);
    }

}
