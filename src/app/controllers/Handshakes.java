package controllers;

import java.util.Date;
import java.util.List;

import models.Handshake;
import models.Handshake.Status;
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
        request.title = "REQUEST FOR: " + offer.title;
        request.save();

    	Handshake handshakeItem = new Handshake();
        handshakeItem.status = Status.WAITING_APPROVAL;
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
	offer.title = "OFFER FOR: " + request.title;
	offer.save();

	Handshake handshakeItem = new Handshake();
    handshakeItem.status = Status.WAITING_APPROVAL;
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

    public static void list() {
        User user = getConnectedUser();
        List<Handshake> handshakes = Handshake.find("offer.user.id = ? or request.user.id = ?", user.id, user.id).fetch();
        render(handshakes);
    }

    public static void accept(Long handshakeId) {
        Handshake handshake = Handshake.findById(handshakeId);
        handshake.status = Status.ACCEPTED;
        handshake.save();
        show(handshakeId);
    }
    
    public static void start(Long handshakeId) {
        Handshake handshake = Handshake.findById(handshakeId);
        handshake.status = Status.STARTED;
        handshake.save();
        show(handshakeId);
    }
    
    public static void cancel(Long handshakeId) {
        Handshake handshake = Handshake.findById(handshakeId);
        handshake.status = Status.CANCELLED;
        handshake.save();
        show(handshakeId);
    }
    
    public static void end(Long handshakeId) {
        Handshake handshake = Handshake.findById(handshakeId);
        handshake.status = Status.DONE;
        handshake.save();
        show(handshakeId);
    }
}
