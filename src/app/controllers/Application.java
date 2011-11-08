package controllers;

import java.util.Date;

import controllers.Secure.Security;

import models.City;
import models.County;
import models.User;
import models.Offer;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.*;

public class Application extends Controller
{   
    @Before
	static void setConnectedUser() {
	//    System.out.println("APPLICATION IS IN CONNECTED USER");
	boolean isConnected = Security.isConnected();
	renderArgs.put("isLoggedIn", isConnected);
	if(isConnected) {
	    User user = User.find("byEmail", Security.connected()).first();
	    renderArgs.put("user", user);
	}
    }
    public static void index()
    {
	render();
	// Create a new user and save it
	/*		User newUser = new User();
			newUser.email = "bob@gmail.com";
			newUser.password = "secret";
			newUser.nickname = "bob";
			newUser.fullname = "Bob";
			newUser.address = "address";
			//newUser.avatar = new UploadedFile();
			newUser.balance = 0;
			newUser.birthday = new Date();
			newUser.city = new City();
			newUser.county = new County();
			newUser.isAdmin = false;
			newUser.job = "job";
			newUser.phone = "phone";
			newUser.registrationDate = new Date();
			newUser.reputation = 0;
			newUser.status = User.Status.WAITING_CONFIRMATION;
			newUser.save();

			// Retrieve the user with e-mail address bob@gmail.com
			User bob2 = User.find("byEmail", "bob@gmail.com").first();

			render(bob2);*/
		
    }

    public static void register() {
	render();
    }

    public static void createOffer() {
	render();
    }

    public static void finalizeOffer(Offer offerItem) {
	render(offerItem);
    }

    public static void createRequest() {
	render();
    }

    public static void doCreateOfferItem(@Valid Offer offerItem) {
	if (validation.hasErrors()) {
	    params.flash();
	    validation.keep();
	    createOffer();
	}
	finalizeOffer(offerItem);
    }

    public static void finalizeRequest() {
	render();
    }

}