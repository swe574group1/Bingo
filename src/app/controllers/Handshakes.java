package controllers;

import java.util.List;

import models.Handshake;
import models.Request;
import play.data.validation.Required;
import play.mvc.Controller;
import service.SearchService;
import service.SearchService.SearchQuery;
import service.SearchService.SearchQuery.SortDirection;
import service.SearchService.SearchQuery.SortField;
import service.SearchService.SearchResult;
import service.SearchService.Type;

public class Handshakes extends Controller
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
}
