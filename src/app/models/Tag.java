package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Tag extends Model
{
    @ManyToOne
    public Offer offer;

    @ManyToOne
    public Request request;
    
    public String name;

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
