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

	Query applicationsQuery = JPA.em().createQuery("from " + Handshake.class.getName() + " where requesterId=" + requestOwner.id + " and request_id=" + requestItem.id + " and status='WAITING_APPROVAL'");
	List<Object[]> applications = applicationsQuery.getResultList();
	List<Handshake> applicationList = new ArrayList(applications);

	AbstractMap<User, Handshake> userApplications = new HashMap();
	
	for (Handshake handshakeItem : applicationList) {
	    User applicant = User.findById(handshakeItem.offererId);
	    userApplications.put(applicant, handshakeItem);
	}
	
	Boolean someoneElsesRequest = (user != requestOwner);
	render(user, requestItem, requestOwner, someoneElsesRequest, hasApplied, userApplications, handshakeId);
    }
    
    /*public static void search(String phrase) {
	User user = getConnectedUser();

	Query openRequestsQuery = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
	List<Object[]> openRequestsList = openRequestsQuery.getResultList();
	List<Request> allRequests = new ArrayList(openRequestsList);
	List<Request> foundRequests = MatchService.match(allRequests, phrase);

	render(user, foundRequests, allRequests, phrase);
    }*/    
    
    public static void search(String phrase, String location, String county_id, String district_id, String reocc, String m1, String t2, String w3, String t4, String f5, String s6, String s7, String tFrom, String tTo) {
    	User user = getConnectedUser();

    	if(location == null) location = "0";
    	Query openRequestsQuery;   
    	String showFiltered = null;
    	String dayHoursFilter = "";
    	String originalPhrase = phrase;
    	
    	if(phrase != null && phrase.length() > 0)
    	{
    		if(phrase.toUpperCase().contains("ING"))
    		{
    			phrase = phrase.toUpperCase().replace("ING","");
    		}
    	}
    	
    	if(reocc != null && reocc.contains("1"))
    	{
    		dayHoursFilter = " and reoccure = True ";
    		
    		if(m1 != null && m1.contains("on"))
    			dayHoursFilter += " and is_rec_monday = True ";
    		
    		if(t2 != null && t2.contains("on"))
    			dayHoursFilter += " and is_rec_tuesday = True ";
    		
    		if(w3 != null && w3.contains("on"))
    			dayHoursFilter += " and is_rec_wednesday = True ";
    		
    		if(t4 != null && t4.contains("on"))
    			dayHoursFilter += " and is_rec_thursday = True ";
    		
    		if(f5 != null && f5.contains("on"))
    			dayHoursFilter += " and is_rec_friday = True ";
    		
    		if(s6 != null && s6.contains("on"))
    			dayHoursFilter += " and is_rec_saturday = True ";
    		
    		if(s7 != null && s7.contains("on"))
    			dayHoursFilter += " and is_rec_sunday = True ";
    		
    		Integer tFromInt = 1;
    		Integer tToInt = 1;
    		
    		if(tFrom != null && tFrom.length() > 0)
    		{
    			tFromInt = Integer.valueOf(tFrom);
    		}
    		
    		if(tTo!= null && tTo.length() > 0)
    		{
    			tToInt = Integer.valueOf(tTo);
    		}
    		
    		if(tFromInt != tToInt)
    		{
    			dayHoursFilter += " and ((reocc_start_hour_val < " +  tFromInt.toString() + " and reocc_end_hour_val > " + tFromInt.toString() + ")"; 
    			dayHoursFilter += " or (reocc_start_hour_val <" + tToInt.toString() + " and reocc_end_hour_val > " + tToInt.toString() + ")";
    			dayHoursFilter += " or (reocc_start_hour_val >" + tFromInt.toString() + " and reocc_end_hour_val < " + tToInt.toString() + "))";
    		}
    	}
    	
    	if(location.contains("1"))
    	{
    		Query openRequestsQueryAll = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
    		List<Object[]> openRequestsListAll = openRequestsQueryAll.getResultList();
    		
    		String addStr = "";
    		
    		if(district_id != null && district_id.length() > 0)
    		{
    			addStr = " and district_id =" + district_id;
    		}
    		else if(county_id != null && county_id.length() > 0)
    		{
    			addStr = " and county_id =" + county_id;
    		}
    		    		
    		openRequestsQuery = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING' and (is_virtual is null or is_virtual = False) " + addStr + dayHoursFilter);
    		List<Object[]> openRequestsList = openRequestsQuery.getResultList();
        	List<Request> allRequests = new ArrayList(openRequestsList);
        	
        	List<Request> foundRequests = MatchService.match(allRequests, phrase);

        	if(phrase == null || phrase.length() == 0)
        	{
        		showFiltered= "1";
        		foundRequests = allRequests;
        	}
        	
        	allRequests = new ArrayList(openRequestsListAll);
        	
        	phrase = originalPhrase;
       		render(user, foundRequests, allRequests, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7, tFrom, tTo);
    	}
    	else if(location.contains("2"))
    	{   		
    		Query openRequestsQueryAll = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
    		List<Object[]> openRequestsListAll = openRequestsQueryAll.getResultList();
    		
    		openRequestsQuery = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'"+ " and is_virtual = True" + dayHoursFilter);
    		List<Object[]> openRequestsList = openRequestsQuery.getResultList();
        	List<Request> allRequests = new ArrayList(openRequestsList);
        	List<Request> foundRequests = MatchService.match(allRequests, phrase);

        	if(phrase == null || phrase.length() == 0)
        	{
        		showFiltered= "1";
        		foundRequests = allRequests;
        	}
        	
        	allRequests = new ArrayList(openRequestsListAll);
        	phrase = originalPhrase;
       		render(user, foundRequests, allRequests, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7, tFrom, tTo);
    	}
    	else 
    	{
    		Query openRequestsQueryAll = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'");
    		List<Object[]> openRequestsListAll = openRequestsQueryAll.getResultList();
    		
    		openRequestsQuery = JPA.em().createQuery("from " + Request.class.getName() + " where status is 'WAITING'" + dayHoursFilter);
    		List<Object[]> openRequestsList = openRequestsQuery.getResultList();
        	List<Request> allRequests = new ArrayList(openRequestsList);
        	List<Request> foundRequests = MatchService.match(allRequests, phrase);
        	
        	if(phrase == null || phrase.length() == 0)
        	{
        		showFiltered= "1";
        		foundRequests = allRequests;
        	}
        	
        	allRequests = new ArrayList(openRequestsListAll);
        	phrase = originalPhrase;
        	render(user, foundRequests, allRequests, phrase, location, county_id, district_id, showFiltered, reocc, m1, t2, w3, t4, f5, s6, s7, tFrom, tTo);
    	}
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
