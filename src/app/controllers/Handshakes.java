package controllers;

import java.util.List;

import models.Handshake;
import models.Request;
import models.Offer;
import models.Tag;
import models.User;
import play.data.validation.Required;
import play.mvc.Controller;
import service.SearchService;
import service.SearchService.SearchQuery;
import service.SearchService.SearchQuery.SortDirection;
import service.SearchService.SearchQuery.SortField;
import service.SearchService.SearchResult;
import service.SearchService.Type;
import play.*;
import play.mvc.*;
 
import java.util.*;
// Require Login
@With(Secure.class)
public class Handshakes extends BaseController
{
    public static void user(Long userId)
	{
	    if (userId == null) {
		error("missing userId");
	    }

	    List<Handshake> handshakes = Handshake.find("offer.user.id = ? or request.user.id = ?", userId, userId).fetch();

	    render(handshakes);
	}

    public static void detail(Long requestId)
	{
	    if (requestId == null) {
		error("missing requestId");
	    }

	    Handshake handshake = Handshake.findById(requestId);

	    render(handshake);
	}

    public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
	{
	    SearchQuery query = new SearchQuery();
	    query.tags = tags;
	    query.sortField = sortField;
	    query.sortDirection = sortDirection;
	    SearchResult<Handshake> searchResult = SearchService.search(Type.HANDSHAKE, query);

	    List<Handshake> handshakes = searchResult.entities;
	    render(handshakes);
	}

    public static void bindToOffer(Long id, User user) {
	Offer offer = Offer.findById(id);
	Handshake handshakeItem = new Handshake(offer, user);
	handshakeItem.save();
	render(handshakeItem);
    }

}
