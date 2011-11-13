package controllers;

import java.util.Date;


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

    public static void saveOffer(Offer offerItem) {
	offerItem.save();
	show(offerItem.id);
    }

    public static void show(Long id) {
	Offer offerItem = Offer.findById(id);
	render(offerItem);
    }

}