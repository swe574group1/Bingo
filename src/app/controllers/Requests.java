package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Request;
import models.Tag;
import models.User;
import service.MatchService;
import service.Utils;

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
	Boolean someoneElsesRequest = isSomeoneElses(id);
	render(user, requestItem, someoneElsesRequest);
    }
    
    public static void search(String phrase) {
	User user = getConnectedUser();

	List<Request> allRequests = Request.findAll();
	List<Request> foundRequests = MatchService.match(allRequests, phrase);

	render(user, foundRequests, allRequests, phrase);
    }

    public static void edit(Long id) {
    	Request requestItem = Request.findById(id);
    	renderTemplate("Requests/form.html", requestItem);
    }

    public static Boolean isSomeoneElses(Long requestId) {
	User currentUser = getConnectedUser();
	Request currentRequest = Request.findById(requestId);
	User owner = currentRequest.user;
	return !(currentUser.equals(owner));
    }

    public static void list() {
	User user = getConnectedUser();
	List<Request> requests = Request.find("user.id", user.id).fetch();
	render(user, requests);
    }

}
