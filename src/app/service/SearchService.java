package service;

import java.util.List;

public class SearchService
{
	public enum Type
	{
		OFFER, REQUEST, HANDSHAKE
	}

	public static class SearchQuery
	{
		public enum SortField
		{
			RELEVANCE, TAGS, DATE, CREDIT
		}

		public enum SortDirection
		{
			ASC, DESC
		}

		public List<String> tags;
		public SortField sortField;
		public SortDirection sortDirection;
	}

	public static class SearchResult<T>
	{
		public List<T> entities;
		public int totalMatches;
		public int currentPage;
		public int totalPages;
	}

	public static <T> SearchResult<T> search(Type type, SearchQuery query)
	{
		throw new UnsupportedOperationException();
	}
}
