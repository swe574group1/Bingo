package controllers;

import java.util.Date;
import java.util.List;

import models.Handshake;
import models.Offer;
import models.Request;
import models.User;
import play.mvc.With;
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

//    public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
//	{
//	    SearchQuery query = new SearchQuery();
//	    query.tags = tags;
//	    query.sortField = sortField;
//	    query.sortDirection = sortDirection;
//	    SearchResult<Handshake> searchResult = MatchService.search(Type.HANDSHAKE, query);
//
//	    List<Handshake> handshakes = searchResult.entities;
//	    render(handshakes);
//	}

    public static void bindToOffer(Long id) {
        User user = getConnectedUser();
    	Offer offer = Offer.findById(id);

        Request request = new Request(user);
        request.save();

    	Handshake handshakeItem = new Handshake();
    	handshakeItem.offer = offer;
    	handshakeItem.request = request;
    	handshakeItem.creationDate = new Date();
    	handshakeItem.save();

    	render(handshakeItem);
    }

}
