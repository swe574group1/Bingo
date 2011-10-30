package controllers;

import java.util.List;

import models.Offer;
import play.data.validation.Required;
import play.mvc.Controller;
import service.SearchService;
import service.SearchService.SearchQuery;
import service.SearchService.SearchQuery.SortDirection;
import service.SearchService.SearchQuery.SortField;
import service.SearchService.SearchResult;
import service.SearchService.Type;

public class Offers extends Controller
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

	public static void search(List<String> tags, SortField sortField, SortDirection sortDirection)
	{
		SearchQuery query = new SearchQuery();
		query.tags = tags;
		query.sortField = sortField;
		query.sortDirection = sortDirection;
		SearchResult<Offer> searchResult = SearchService.search(Type.OFFER, query);

		List<Offer> offers = searchResult.entities;
		render(offers);
	}
}
