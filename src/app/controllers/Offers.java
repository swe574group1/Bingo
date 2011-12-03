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
    public static void create() {
	render();
    }

    public static void doCreate(String tags, Offer offerItem) {
    	User user = getConnectedUser();

	String[] tagsArr = tags.split(",");
	List<Tag> tagsList = new ArrayList<Tag>();
	for (String tagString : tagsArr) {
	    Tag tag = new Tag(offerItem, tagString.trim());
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
	Boolean someoneElsesOffer = isSomeoneElses(id);
    	render(user, offerItem, someoneElsesOffer);
    }

    public static void search() {
    	User user = getConnectedUser();
    	List<Offer> offers = Offer.find("isFinalized", true).fetch();
    	render(user, offers);
    }

    public static void edit(Long id) {
	Offer offerItem = Offer.findById(id);
	render(offerItem);
    }

    public static Boolean isSomeoneElses(Long offerId) {
	User currentUser = getConnectedUser();
	Offer currentOffer = Offer.findById(offerId);
	User owner = currentOffer.user;
	return !(currentUser.equals(owner));
    }

}
