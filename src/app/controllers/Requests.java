package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import models.Offer;

import models.Request;
import models.Tag;
import models.User;
import service.MatchService;
import service.Utils;
import models.Handshake;

import play.db.jpa.JPA;

public class Requests extends BaseController
{
    public static void create() {
	Request requestItem = new Request();
	requestItem.tags = new ArrayList<Tag>();
	renderTemplate("Requests/form.html", requestItem);
    }

    public static void doCreate(String tags, Request requestItem) {
	User user = getConnectedUser();

	boolean isCreate = requestItem.id == null;
	
	if (!isCreate) {
	    Tag.delete("request.id", requestItem.id);
	}

	List<String> tagsListString = Utils.parseTags(tags);
	List<Tag> tagsList = new ArrayList<Tag>();
	for (String tagString : tagsListString) {
	    Tag tag = new Tag(requestItem, tagString);
	    tagsList.add(tag);
	}
	requestItem.tags = tagsList;

	validation.valid(requestItem);
	if (validation.hasErrors()) {
	    renderTemplate("Requests/form.html", requestItem);
	}
	
	requestItem.user = user;
	requestItem.save();
	
	show(requestItem.id);
    }

    public static void save(Long requestId) {
	Request requestItem = Request.findById(requestId);
	requestItem.save();
	show(requestItem.id);
    }

    public static void show(Long requestId) {
	Request requestItem = Request.findById(requestId);
	render(requestItem);
    }

    public static void showAfterEdit(Long requestId) {
	Request requestItem = Request.findById(requestId);
	Boolean isOldRequest = true;
	render(requestItem, isOldRequest);
    }

    public static void showDetails(Long id) {
	User user = getConnectedUser();
	Request requestItem = Request.findById(id);
	User requestOwner = requestItem.user;

	Long handshakeId = new Long(0L); // variable to store the id of the matched handshake
	Query handshakeQuery = JPA.em().createQuery("from " + Handshake.class.getName() + " where request.id=" + requestItem.id); // handshakes which have been initiated with the current request's id
	List<Object[]> handshakeList = handshakeQuery.getResultList(); // list of matching handshakes
	Boolean hasApplied = false; // inititate hasApplied boolean to false
	
	for(Object singleHandshake : handshakeList) { // iterate over handshakes
	    Handshake handshakeItem = (Handshake) singleHandshake; // type casting
	    Offer offerItem = handshakeItem.offer; // the offer belonging to the current iteration's handshake
	    hasApplied = (offerItem.user == user); // if the user of the request is equal to the current user, set hasApplied to true
	    if (hasApplied) { // store the matched handhshake's id and break out of the for loop if we know user has applied to the current request
		handshakeId = handshakeItem.id;
		break;
	    }
	}
	
	Boolean someoneElsesRequest = (user != requestOwner);
	render(user, requestItem, requestOwner, someoneElsesRequest, hasApplied, handshakeId);
    }
    
    public static void search(String phrase) {
	User user = getConnectedUser();

	Query openRequestsQuery = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
	List<Object[]> openRequestsList = openRequestsQuery.getResultList();
	List<Request> allRequests = new ArrayList(openRequestsList);
	List<Request> foundRequests = MatchService.match(allRequests, phrase);

	render(user, foundRequests, allRequests, phrase);
    }

    public static void edit(Long id) {
    	Request requestItem = Request.findById(id);
    	renderTemplate("Requests/form.html", requestItem);
    }

    public static void list() {
	User user = getConnectedUser();
	List<Request> requests = Request.find("user.id", user.id).fetch();
	render(user, requests);
    }

}
