package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import service.Matchable;

@Entity
public class Offer extends Service implements Matchable
{
	@OneToMany(mappedBy="offer")
    public List<OfferComment> comments;
	
	public Offer() {
    	this.tags = new ArrayList<Tag>();
    	this.status = Status.CREATED;
    }
	
	public Offer(User user) {
    	this.tags = new ArrayList<Tag>();
    	this.owner = user;
    }
	
	public Offer(User user, Request request) {
    	this();
		this.title = request.title;
		this.description = request.description;
    }

	@Override
	protected void createSessions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateSessions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteSessions() {
		// TODO Auto-generated method stub
		
	}
}
