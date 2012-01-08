package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap;
import java.util.HashMap;

import javax.persistence.Query;

import models.Offer;
import models.Request;
import models.Tag;
import models.User;
import models.Handshake;
import service.MatchService;
import service.Utils;

import play.db.jpa.JPA;


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
		List<String> tagsListString = Utils.parseTags(tags);
		List<Tag> tagsList = new ArrayList<Tag>();
		for (String tagString : tagsListString) {
		    Tag tag = new Tag(offerItem, tagString);
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

    public static void showAfterEdit(Long id) {
	Offer offerItem = Offer.findById(id);
	Boolean isOldOffer = true;
	render(offerItem, isOldOffer);
    }

    public static void showDetails(Long id) {
    	User user = getConnectedUser(); // user who is inspecting the offer
    	Offer offerItem = Offer.findById(id); // the offer being inspected
	User offerOwner = offerItem.user; // owner of the offer

	Long handshakeId = new Long(0L); // variable to store the id of the matched handshake
	Query handshakeQuery = JPA.em().createQuery("from " + Handshake.class.getName() + " where offer.id=" + offerItem.id); // handshakes which have been initiated with the current offer's id
	List<Object[]> handshakeList = handshakeQuery.getResultList(); // list of matching handshakes

	Boolean hasApplied = false; // inititate hasApplied boolean to false
	
	for(Object singleHandshake : handshakeList) { // iterate over handshakes
	    Handshake handshakeItem = (Handshake) singleHandshake; // type casting
	    Request requestItem = handshakeItem.request; // the request belonging to the current iteration's handshake
	    hasApplied = (requestItem.user == user); // if the user of the request is equal to the current user, set hasApplied to true
	    if (hasApplied) { // store the matched handhshake's id and break out of the for loop if we know user has applied to the current offer
		handshakeId = handshakeItem.id;
		break;
	    }
	}

	Query applicationsQuery = JPA.em().createQuery("from " + Handshake.class.getName() + " where offererId=" + offerOwner.id + " and offer_id=" + offerItem.id + " and status='WAITING_APPROVAL'");
	List<Object[]> applications = applicationsQuery.getResultList();
	List<Handshake> applicationList = new ArrayList(applications);

	AbstractMap<User, Handshake> userApplications = new HashMap();
	
	for (Handshake handshakeItem : applicationList) {
	    User applicant = User.findById(handshakeItem.requesterId);
	    userApplications.put(applicant, handshakeItem);
	}
	
	Boolean someoneElsesOffer = (user != offerItem.user);
    	render(user, offerItem, offerOwner, someoneElsesOffer, hasApplied, userApplications);
    }

    public static void search(String phrase, String location) {
    	User user = getConnectedUser();

    	Query openOffersQuery = JPA.em().createQuery("from " + Offer.class.getName() + " where status is 'WAITING'");
		List<Object[]> openOffersList = openOffersQuery.getResultList();
    	List<Offer> allOffers = new ArrayList(openOffersList);
    	List<Offer> foundOffers = MatchService.match(allOffers, phrase);

    	render(user, foundOffers, allOffers, phrase, location);
    }

    public static void edit(Long id) {
    	Offer offerItem = Offer.findById(id);
    	renderTemplate("Offers/form.html", offerItem);
    }

    public static void list() {
	User user = getConnectedUser();
	List<Offer> offers = Offer.find("user.id", user.id).fetch();
	render(user, offers);
    }
}
