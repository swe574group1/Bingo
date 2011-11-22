package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Offer;
import models.Tag;
import models.User;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Offers extends BaseController
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
    
    public static void create() {
	render();
    }

    public static void doCreate(String tags, Offer offerItem) {
    	User user = getConnectedUser();

		String[] tagsArr = tags.split(",");
		List<Tag> tagsList = new ArrayList<Tag>();
		for (String tagString : tagsArr) {
			Tag tag = new Tag(offerItem, tagString);
			tagsList.add(tag);
		}
		offerItem.tags = tagsList;

		validation.valid(offerItem);
		if (validation.hasErrors()) {
		    params.flash();
		    validation.keep();
		    create();
		}
		offerItem.user = user;
		offerItem.isFinalized = false;
		offerItem.save();

		finalize(offerItem.id);
    }
    
    public static void finalize(Long offerId) {
    	Offer offerItem = Offer.findById(offerId);
    	render(offerItem);
    }

    public static void save(Long offerId) {
    	Offer offerItem = Offer.findById(offerId);
    	offerItem.isFinalized = true;
		offerItem.save();
		show(offerItem.id);
    }

    public static void show(Long id) {
	Offer offerItem = Offer.findById(id);
	render(offerItem);
    }

    public static void showDetails(Long id) {
    	User user = getConnectedUser();
    	Offer offerItem = Offer.findById(id);
    	render(user, offerItem);
    }

    public static void search() {
    	User user = getConnectedUser();
    	List<Offer> offers = Offer.find("isFinalized", true).fetch();
    	render(user, offers);
    }

}
