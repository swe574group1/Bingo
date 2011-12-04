package service;

import java.util.ArrayList;
import java.util.List;

import models.Offer;
import models.Tag;

public class MatchService
{
	public static <M extends Matchable> List<M> match(List<M> allItems, String phrase)
    {
	    List<String> tags = Utils.parseTags(phrase);

        List<M> matchedItems = new ArrayList<M>();
        for(M item : allItems) {
            boolean found = false;
            for(Tag tag : item.getTags()) {
                for(String tagString : tags) {
                    if(tag.name.equals(tagString)) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                matchedItems.add(item);
            }
        }

        return matchedItems;
    }
}
