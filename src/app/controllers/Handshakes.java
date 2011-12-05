package controllers;

import java.util.Date;
import java.util.List;

import models.Handshake;
import models.Offer;
import models.Request;
import models.User;
import play.mvc.With;
// Require Login
@With(Secure.class)
public class Handshakes extends BaseController
{
    public static void bindToOffer(Long id) {
        User user = getConnectedUser();
    	Offer offer = Offer.findById(id);

        Request request = new Request(user, offer);
        request.save();

    	Handshake handshakeItem = new Handshake();
    	handshakeItem.offer = offer;
    	handshakeItem.request = request;
    	handshakeItem.creationDate = new Date();
    	handshakeItem.save();

    	renderTemplate("Handshakes/bind.html", handshakeItem);
    }

    public static void bindToRequest(Long id, Integer credits) {
	User user = getConnectedUser();
	Request request = Request.findById(id);

	Offer offer = new Offer(user, credits, request);
	offer.save();

	Handshake handshakeItem = new Handshake();
	handshakeItem.offer = offer;
	handshakeItem.request = request;
	handshakeItem.creationDate = new Date();
	handshakeItem.save();

	renderTemplate("Handshakes/bind.html", handshakeItem);
    }

    public static void show(Long id) {
	Handshake handshake = Handshake.findById(id);
	render(handshake);
    }

}
