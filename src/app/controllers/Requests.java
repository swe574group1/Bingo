package controllers;

import java.util.List;

import models.*;
import play.data.validation.*;
import play.mvc.Controller;

public class Requests extends BaseController
{
    
    public static void create() {
	render();
    }

    public static void doCreate(String tags, Request requestItem) {
	User user = getConnectedUser();

	String[] tagsArr = tags.split(",");
	List<Tag> tagList = new ArrayList<Tag>();
	for (String tagString : tagsArr) {
	    Tag tag = new Tag(requestItem, tagString);
	    tagList.add(tag);
	}
	requestItem.tags = tagList;

	validation.valid(offerItem);
	if (validation.hasError()) {
	    params.flash();
	    validation.keep();
	    create();
	}

	requestItem.user = user;
	requestItem.isFinalized = false;
	requestItem.save();
	
	finalize(requestItem.id);
    }

    public static void finalize(Long requestId) {
	Request requestItem = Request.findById(requestId);
	render(requestItem);
    }

    public static void save(Long requestId) {
	Request requestItem = Request.findById(requestId);
	requestItem.isFinalized = true;
	requestItem.save();
	show(requestItem.id);
    }

    public static void show(Request requestItem) {
	render(requestItem);
    }

    public static void listBelongingToUser(String email) {
	List<Request> requests = Request.find("byUserEmail", email).fetch();
	render(requests);
    }
    
    public static void showDetails(Long id) {
	User user = getConnectedUser();
	Request requestItem = Request.findById(id);
	render(user, requestItem);
    }

    public static void search(User user) {
	User user = getConnectedUser();
	List<Request> requests = Request.find("isFinalized", true).fetch();
	render(user, offers);
    }

}
