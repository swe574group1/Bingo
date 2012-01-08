package service;

import java.util.ArrayList;
import java.util.List;

import models.Offer;
import models.Tag;

public class MatchService
{
	/*
	public static <M extends Matchable> List<M> match(List<M> allItems, String phrase)
    {
	    List<String> tags = Utils.parseTags(phrase);

        List<M> matchedItems = new ArrayList<M>();
        for(M item : allItems) {
            boolean found = false;
            for(Tag tag : item.getTags()) {
                for(String tagString : tags) {
                    if(tag.name.toUpperCase().contains(tagString.toUpperCase())) {
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
    }*/
	
    
	public static <M extends Matchable> List<M> match(List<M> allItems, String phrase)
    {
	    List<String> tags = Utils.parseTags(phrase);

        List<M> matchedItems = new ArrayList<M>();    
                
        for(M item : allItems) {
            Integer foundCount = 0;
            
            for(Tag tag : item.getTags()) {
                for(String tagString : tags) {
                    if(tag.name.toUpperCase().contains(tagString.toUpperCase())) {
                    	foundCount++;
                        break;
                    }
                }
            }
            
            if (foundCount == 1) {
		        String Desc = item.getDescription();
		        
		        if(Desc!= null && Desc.length()>0 && phrase!=null && phrase.length()>0)
		        {
	                if(Desc.toUpperCase().contains(phrase.toUpperCase())) {
	                	foundCount++;
	                }
		        }
            }
            
            if (foundCount == 2) {
		        String Title = item.getTitle();
		        
		        if(Title!= null && Title.length()>0 && phrase!=null && phrase.length()>0)
		        {
	                if(Title.toUpperCase().contains(phrase.toUpperCase())) {
	                	foundCount++;
	                }	
		        }
            }
            
            if (foundCount == 3) {
            	matchedItems.add(item);
            }
        }


        for(M item : allItems) {
            Integer foundCount = 0;
            
            for(Tag tag : item.getTags()) {
                for(String tagString : tags) {
                    if(tag.name.toUpperCase().contains(tagString.toUpperCase())) {
                    	foundCount++;
                        break;
                    }
                }
            }
            
           
	        String Desc = item.getDescription();
	        
	        if(Desc!= null && Desc.length()>0 && phrase!=null && phrase.length()>0)
	        {
                if(Desc.toUpperCase().contains(phrase.toUpperCase())) {
                	foundCount++;
                }
	        }
            	            
	        String Title = item.getTitle();
	        
	        if(Title!= null && Title.length()>0 && phrase!=null && phrase.length()>0)
	        {
                if(Title.toUpperCase().contains(phrase.toUpperCase())) {
                	foundCount++;
                }	
	        }
       	            
            if (foundCount == 2) {
            	if(!matchedItems.contains(item))
            		matchedItems.add(item);
            }
        }

	    for(M item : allItems) {
	        Integer foundCount = 0;
	        
	        for(Tag tag : item.getTags()) {
	            for(String tagString : tags) {
	                if(tag.name.toUpperCase().contains(tagString.toUpperCase())) {
	                	foundCount++;
	                    break;
	                }
	            }
	        }		        
	       
	        String Desc = item.getDescription();
	        
	        if(Desc!= null && Desc.length()>0 && phrase!=null && phrase.length()>0)
	        {
	            if(Desc.toUpperCase().contains(phrase.toUpperCase())) {
	            	foundCount++;
	            }
	        }
	        		        
	        String Title = item.getTitle();
	        
	        if(Title!= null && Title.length()>0 && phrase!=null && phrase.length()>0)
	        {
	            if(Title.toUpperCase().contains(phrase.toUpperCase())) {
	            	foundCount++;
	            }	
	        }
	   	            
	        if (foundCount > 0) {
	        	if(!matchedItems.contains(item))
	        		matchedItems.add(item);
	        }
	    }
        
        return matchedItems;
    }
}