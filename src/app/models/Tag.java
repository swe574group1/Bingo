package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Tag extends Model
{
    @ManyToOne
    @Required
    public Offer offer;

    @ManyToOne
    @Required
    public Request request;
    
    public String name;

    public Boolean is_offer;
    public Boolean is_request;
    
    public Tag(Offer offer, String name)
	{
    	this.offer = offer;
    	this.name = name;
	}

    public Tag(Request request, String name) {
	this.request = request;
	this.name = name;
    }
    
    @Override
    public String toString()
    {
    	return name;
    }

}
