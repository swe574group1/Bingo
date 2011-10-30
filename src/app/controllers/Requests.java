package controllers;

import java.util.List;

import models.Request;
import play.data.validation.Required;
import play.mvc.Controller;
import service.SearchService;
import service.SearchService.SearchQuery;
import service.SearchService.SearchQuery.SortDirection;
import service.SearchService.SearchQuery.SortField;
import service.SearchService.SearchResult;
import service.SearchService.Type;

public class Requests extends Controller
{
	public static void user(Long userId)
	{
		if (userId == null) {
			error("missing userId");
		}

		List<Request> requests = Request.find("user.id", userId).fetch();

		render(requests);
	}

	public static void detail(Long requestId)
	{
		if (requestId == null) {
			error("missing requestId");
		}

		Request request = Request.findById(requestId);

		render(request);
	}

	public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
	{
		SearchQuery query = new SearchQuery();
		query.tags = tags;
		query.sortField = sortField;
		query.sortDirection = sortDirection;
		SearchResult<Request> searchResult = SearchService.search(Type.REQUEST, query);

		List<Request> requests = searchResult.entities;
		render(requests);
	}
}
