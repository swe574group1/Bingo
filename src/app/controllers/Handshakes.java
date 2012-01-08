package controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;

import play.db.jpa.JPA;


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

        Request request = new Request(user, offer);
        request.title = "REQUEST FOR: " + offer.title;

    	Handshake handshakeItem = new Handshake();
        handshakeItem.status = Status.WAITING_APPROVAL;
    	handshakeItem.offer = offer;
    	handshakeItem.request = request;
    	handshakeItem.creationDate = new Date();

	handshakeItem.requesterId = request.user.id;
	handshakeItem.offererId = offer.user.id;
	
	handshakeItem.isOriginallyAnOffer = true;
	handshakeItem.offererStart = false;
	handshakeItem.requesterStart = false;

	offer.save();
	request.save();
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

    public static void bindToRequest(Long id) {
	User user = getConnectedUser();
	Request request = Request.findById(id);

	Offer offer = new Offer(user, 7, request); // magic number 7: fixed honey comb for applying to requests
	offer.title = "OFFER FOR: " + request.title;
	offer.save();

	Handshake handshakeItem = new Handshake();
	handshakeItem.status = Status.WAITING_APPROVAL;
	handshakeItem.offer = offer;
	handshakeItem.request = request;
	handshakeItem.creationDate = new Date();

	handshakeItem.requesterId = request.user.id;
	handshakeItem.offererId = offer.user.id;

	handshakeItem.isOriginallyAnOffer = false;
	handshakeItem.offererStart = false;
	handshakeItem.requesterStart = false;

	handshakeItem.save();

	Boolean created = true;
	renderTemplate("Handshakes/bind.html", handshakeItem, created);
    }

    public static void show(Long id) {
	User currentUser = getConnectedUser();
	Handshake handshake = Handshake.findById(id);
	List<Comment> comments = Comment.find("handshake = ?", handshake).fetch();
	Boolean originallyOffer = handshake.isOriginallyAnOffer;
	Integer commentCount = comments.size();
	
	render(currentUser, handshake, comments, originallyOffer, commentCount);
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
        Handshake handshakeItem = Handshake.findById(handshakeId);
        handshakeItem.status = Status.ACCEPTED;
        handshakeItem.save();

	Offer offer = Offer.findById(handshakeItem.offer.id);
	offer.status = Offer.Status.HANDSHAKED;
	offer.save();
	

	Request request = Request.findById(handshakeItem.request.id);
	request.status = Request.Status.HANDSHAKED;
        request.save();

	/* mark applications which were not accepted as obsolete */
	Query rejectedHandshakeQuery = JPA.em().createQuery("from " + Handshake.class.getName() + " where offer_id=" + handshakeItem.offer.id + " and request_id!=" + handshakeItem.request.id);
	List<Object[]> rejectedList = rejectedHandshakeQuery.getResultList();
	List<Handshake> rejectedHandshakes = new ArrayList(rejectedList);
	for (Handshake rejected : rejectedHandshakes) {
	    rejected.status = Status.REJECTED;
	    rejected.save();
	}
	
	Boolean accepted = true;
        renderTemplate("Handshakes/bind.html", handshakeItem, accepted);
    }

    
    public static void ignore(Long handshakeId) {
	Handshake handshakeItem = Handshake.findById(handshakeId);
	handshakeItem.status = Status.IGNORED;
	handshakeItem.save();

	renderTemplate("Handshakes/bind.html", handshakeItem);
    }
    
    public static void start(Long handshakeId) {
        Handshake handshakeItem = Handshake.findById(handshakeId);

	Boolean startedByBoth, startedByOne;
	
	User user = getConnectedUser();
	if (user.id == handshakeItem.offererId) {
	    handshakeItem.offererStart = true;
	} else if (user.id == handshakeItem.requesterId) {
	    handshakeItem.requesterStart = true;
	}
        handshakeItem.save();

	startedByOne = (handshakeItem.offererStart || handshakeItem.requesterStart);
	startedByBoth = (handshakeItem.offererStart && handshakeItem.requesterStart);
	
	if (startedByBoth) {
	    User offerer = User.findById(handshakeItem.offererId);
	    User requester = User.findById(handshakeItem.requesterId);
	    offerer.balance += handshakeItem.offer.credit;
	    requester.balance -= handshakeItem.offer.credit;
	    offerer.save();
	    requester.save();
	    handshakeItem.status = Status.STARTED;
	    handshakeItem.save();
	}

        renderTemplate("Handshakes/bind.html", handshakeItem, startedByBoth, startedByOne);
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
	show(handshakeId);
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
