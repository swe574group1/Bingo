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

public class Offers extends Controller
{
    public static void user(Long userId)
	{
	    if (userId == null) {
		error("missing userId");
	    }

	    List<Offer> offers = Offer.find("user.id", userId).fetch();

	    render(offers);
	}

    public static void detail(Long offerId)
    	{
    	    if (offerId == null) {
    		error("missing offerId");
    	    }

    	    Offer offer = Offer.findById(offerId);

    	    render(offer);
    	}

    /* Commented out because throws exception and I have no idea how to fix */
    // public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
    // 	{
    // 	    SearchQuery query = new SearchQuery();
    // 	    query.tags = tags;
    // 	    query.sortField = sortField;
    // 	    query.sortDirection = sortDirection;
    // 	    SearchResult<Offer> searchResult = SearchService.search(Type.OFFER, query);

    // 	    List<Offer> offers = searchResult.entities;
    // 	    render(offers);

    // 	}

    public static void create(User user) {
	render(user);
    }

    public static void doCreate(@Valid Offer offerItem, User user) {
	if (validation.hasErrors()) {
	    params.flash();
	    validation.keep();
	    create(user);
	}
	finalize(offerItem, user);
    }
    
    public static void finalize(Offer offerItem, User user) {
	render(offerItem, user);
    }

    public static void save(Offer offerItem) {
	offerItem.save();
	show(offerItem.id);
    }

    public static void show(Long id) {
	Offer offerItem = Offer.findById(id);
	render(offerItem);
    }

    public static void showDetails(Long id) {
	Offer offerItem = Offer.findById(id);
	render(offerItem);
    }

    public static void search() {
	List<Offer> offers = Offer.all().fetch();
	render(offers);
    }

}
