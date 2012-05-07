package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Parent;

import play.db.jpa.JPA;

/**
 * Represents a comment made for an offer.
 * 
 * @author	Onur Yaman  <onuryaman@gmail.com>
 * @version 2.0
 * @since	2.0
 */
@Entity
public class OfferComment extends Comment
{   
	/**
	 * The offer instance for which the comment was made.
	 */
	@ManyToOne
	public Offer offer;
	
    /**
     * Saves and the current instance.
     * 
     * @since 2.0
     */
    public OfferComment save() {
    	// fetch the entity manager.
    	EntityManager em = JPA.em();
    	
    	// persist, save and return the current instance.
    	em.persist(this.user);
    	em.persist(this.offer);
    	em.persist(this);
    	em.flush();
    	
    	// return the current instance.
    	return this;
    }
}
