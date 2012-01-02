package controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import models.BadgeType;
import models.Comment;
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
	offer.status = Offer.Status.HANDSHAKED;
	offer.save();

        Request request = new Request(user, offer);
        request.title = "REQUEST FOR: " + offer.title;
	request.status = Request.Status.HANDSHAKED;
        request.save();

    	Handshake handshakeItem = new Handshake();
        handshakeItem.status = Status.WAITING_APPROVAL;
    	handshakeItem.offer = offer;
    	handshakeItem.request = request;
    	handshakeItem.creationDate = new Date();

	handshakeItem.requesterId = request.user.id;
	handshakeItem.offererId = offer.user.id;
	
    	handshakeItem.save();

	Boolean created = true;
    	renderTemplate("Handshakes/bind.html", handshakeItem, created);
    }

    public static void cancelApplication(Long handshakeId) {
	Handshake handshakeItem = Handshake.findById(handshakeId);
	handshakeItem.delete();
	Boolean cancelled = true;
	renderTemplate("Handshakes/bind.html", cancelled);
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

	handshakeItem.requesterId = request.user.id;
	handshakeItem.offererId = offer.user.id;

	
	handshakeItem.save();

	renderTemplate("Handshakes/bind.html", handshakeItem);
    }

    public static void show(Long id) {
	Handshake handshake = Handshake.findById(id);
	List<Comment> comments = Comment.find("handshake = ?", handshake).fetch();
	render(handshake, comments);
    }

    public static void list() {
        User user = getConnectedUser();
        List<Handshake> handshakes = Handshake.find("offer.user.id = ? or request.user.id = ?", user.id, user.id).fetch();
        render(handshakes);
    }

    public static void search(String phrase) {
        User user = getConnectedUser();

        List<Handshake> allHandshakes = Handshake.findAll();

        render(user, allHandshakes, phrase);
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
        updateBadgeForHandshake(handshake);
        
        show(handshakeId);
    }

    public static void saveComment(Long handshakeId)
    {
        User user = getConnectedUser();
        Comment comment = new Comment();
        comment.user = user;
        comment.handshake = Handshake.findById(handshakeId);
        comment.date = new Date();
        comment.text = request.params.get("message");
        comment.save();
    }

    private static void updateBadgeForHandshake(Handshake handshake){
    	updateBadgeForUser(handshake.offer.user);
    	updateBadgeForUser(handshake.request.user);
    }

    private static void updateBadgeForUser(User user){
    	Date now = new Date();
    	Timestamp sixMonthAgo = new Timestamp(now.getTime() - 86400000*30*6);

    	long count=Handshake.count("(offer.user.id = ? or request.user.id = ?) and creationDate> ?", user.id, user.id,sixMonthAgo);
    	user.badge=BadgeType.NEW_BEE;
    	if(count>=5)
    		user.badge=BadgeType.BUSY_BEE;
    	if(count>=30)
    		user.badge=BadgeType.WORKING_BEE;
    	if(count>=60)
    		user.badge=BadgeType.BUMBLE_BEE;
    	user.save();
    	
    }
}
