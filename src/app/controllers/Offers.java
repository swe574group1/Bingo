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
	
		show(offerItem.id, isCreate);
    }
    
    public static void save(Long offerId) {
    	Offer offerItem = Offer.findById(offerId);
	offerItem.save();
	show(offerItem.id, true);
    }

    public static void show(Long id, Boolean isCreate) {
	Offer offerItem = Offer.findById(id);
	Boolean isOldOffer = !isCreate;
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

	Boolean isOfferOwner = (user == offerOwner);
	Boolean someoneElsesOffer = (user != offerItem.user);
    	render(user, offerItem, offerOwner, someoneElsesOffer, hasApplied, userApplications, isOfferOwner);
    }

    public static void search(String phrase, String location, String county_id, String district_id, String reocc, String m1, String t2, String w3, String t4, String f5, String s6, String s7) {
    	User user = getConnectedUser();

    	if(location == null) location = "0";
    	Query openOffersQuery;   
    	String showFiltered = null;
    	
    	if(location.contains("1"))
    	{
    		Query openOffersQueryAll = JPA.em().createQuery("from " + Offer.class.getName() + " where status is 'WAITING'");
    		List<Object[]> openOffersListAll = openOffersQueryAll.getResultList();
    		
    		String addStr = "";
    		
    		if(district_id != null && district_id.length() > 0)
    		{
    			addStr = " and district_id =" + district_id;
    		}
    		else if(county_id != null && county_id.length() > 0)
    		{
    			addStr = " and county_id =" + county_id;
    		}
    		    		
    		openOffersQuery = JPA.em().createQuery("from " + Offer.class.getName() + " where status is 'WAITING' and (is_virtual is null or is_virtual = False) " + addStr);
    		List<Object[]> openOffersList = openOffersQuery.getResultList();
        	List<Offer> allOffers = new ArrayList(openOffersList);
        	
        	List<Offer> foundOffers = MatchService.match(allOffers, phrase);

        	if(phrase == null || phrase.length() == 0)
        	{
        		showFiltered= "1";
        		foundOffers = allOffers;
        	}
        	
        	allOffers = new ArrayList(openOffersListAll);
        	
       		render(user, foundOffers, allOffers, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7);
    	}
    	else if(location.contains("2"))
    	{   		
    		openOffersQuery = JPA.em().createQuery("from " + Offer.class.getName() + " where status is 'WAITING'"+ " and is_virtual = True");
    		List<Object[]> openOffersList = openOffersQuery.getResultList();
        	List<Offer> allOffers = new ArrayList(openOffersList);
        	List<Offer> foundOffers = MatchService.match(allOffers, phrase);

       		render(user, foundOffers, allOffers, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7);
    	}
    	else 
    	{
    		openOffersQuery = JPA.em().createQuery("from " + Offer.class.getName() + " where status is 'WAITING'");
    		List<Object[]> openOffersList = openOffersQuery.getResultList();
        	List<Offer> allOffers = new ArrayList(openOffersList);
        	List<Offer> foundOffers = MatchService.match(allOffers, phrase);
        	
        	render(user, foundOffers, allOffers, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7);
    	}
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
