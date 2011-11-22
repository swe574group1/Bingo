package controllers;

import java.util.List;

import models.*;
import play.data.validation.*;
import play.mvc.Controller;
import service.SearchService;
import service.SearchService.SearchQuery;
import service.SearchService.SearchQuery.SortDirection;
import service.SearchService.SearchQuery.SortField;
import service.SearchService.SearchResult;
import service.SearchService.Type;

public class Requests extends BaseController
{
    public static void user(Long userId)
	{
	    if (userId == null) {
		error("missing userId");
	    }

	    List<Request> requests = Request.find("user.id", userId).fetch();

	    render(requests);
	}

    public static void detail(Long requestId)
    	{
    	    if (requestId == null) {
    		error("missing requestId");
    	    }

    	    Request request = Request.findById(requestId);

    	    render(request);
     	}
    
    /* commented out because throws exception and I have no idea how to fix */
    // public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
    // 	{
    // 	    SearchQuery query = new SearchQuery();
    // 	    query.tags = tags;
    // 	    query.sortField = sortField;
    // 	    query.sortDirection = sortDirection;
    // 	    SearchResult<Request> searchResult = SearchService.search(Type.REQUEST, query);

    // 	    List<Request> requests = searchResult.entities;
    // 	    render(requests);
    // 	}

    public static void create(User user) {
	render(user);
    }

    public static void doCreate(User user, @Valid Request requestItem) {
	if (validation.hasErrors()) {
	    params.flash();
	    validation.keep();
	    create(user);
	}
	finalize(requestItem);
    }

    public static void finalize(Request requestItem) {
	render(requestItem);
    }

    public static void save(Request requestItem) {
	requestItem.save();
	show(requestItem);
    }

    public static void show(Request requestItem) {
	render(requestItem);
    }

    public static void listBelongingToUser(String email) {
	List<Request> requests = Request.find("byUserEmail", email).fetch();
	render(requests);
    }
    
    public static void showDetails(User user, Long id) {
	Request requestItem = Request.findById(id);
	render(user, requestItem);
    }

    public static void search(User user) {
	List<Request> requests = Request.all().fetch();
	render(user, requests);
    }

}
