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
    	Offer offerItem = new Offer();
    	offerItem.tags = new ArrayList<Tag>();
    	renderTemplate("Offers/form.html", offerItem);
    }

    public static void doCreate(String tags, Offer offerItem) {
    	User user = getConnectedUser();

    	boolean isCreate = offerItem.id == null;

    	if (!isCreate) {
    		// prevent tags to be appended to existing tags on edit
    		Tag.delete("offer.id", offerItem.id);
    	}
		String[] tagsArr = tags.split(",");
		List<Tag> tagsList = new ArrayList<Tag>();
		for (String tagString : tagsArr) {
		    Tag tag = new Tag(offerItem, tagString.trim().toLowerCase());
		    tagsList.add(tag);
		}
		offerItem.tags = tagsList;
	
		validation.valid(offerItem);
		if (validation.hasErrors()) {
		    renderTemplate("Offers/form.html", offerItem);
		}
	
		offerItem.user = user;
		offerItem.save();
	
		show(offerItem.id);
    }
    
    public static void save(Long offerId) {
    	Offer offerItem = Offer.findById(offerId);
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
    	List<Offer> offers = Offer.findAll();
    	render(user, offers);
    }

    public static void edit(Long id) {
    	Offer offerItem = Offer.findById(id);
    	renderTemplate("Offers/form.html", offerItem);
    }

    public static boolean isSomeoneElses(Long offerId) {
	User currentUser = getConnectedUser();
	Offer currentOffer = Offer.findById(offerId);
	User owner = currentOffer.user;
	return !(currentUser.equals(owner));
    }

    public static void list() {
	User user = getConnectedUser();
	List<Offer> offers = Offer.find("user.id", user.id).fetch();
	render(user, offers);
    }
}
