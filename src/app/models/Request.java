package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import service.Matchable;

@Entity
public class Request extends Service implements Matchable
{
	public Boolean isFinalized;
	
	@OneToMany(mappedBy="request")
    public List<RequestComment> comments;
	
	public Request() {
    	this.tags = new ArrayList<Tag>();
    	this.status = Status.CREATED;
    }
	
	public Request(User user) {
    	this.tags = new ArrayList<Tag>();
    	this.owner = user;
    }
	 
	public Request(User user, Offer offer) {
    	this();
		this.title = offer.title;
		this.description = offer.description;
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