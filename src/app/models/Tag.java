package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Tag extends Model
{
    @ManyToOne
    public Offer offer;

    public String name;

    public Tag(Offer offer, String name)
	{
    	this.offer = offer;
    	this.name = name;
	}
    
    @Override
    public String toString()
    {
    	return name;
    }
}
