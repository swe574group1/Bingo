package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;
import service.CreditManager;

@Entity
public abstract class Service extends Model
{
	
	@Required
    public String title;
	
    @Temporal(TemporalType.TIMESTAMP)
    public Date startsAt;
	
    @Temporal(TemporalType.TIMESTAMP)
    public Date endsAt;
	
	@Required
    @Lob
    public String description;
	
	@Required
	@ManyToMany(cascade=CascadeType.ALL)
    public List<Tag> tags;
	
	/**
	 * Parent service. A service is said to be parent
	 * if it represents a set of sessions and child if
	 * it represents a single session of a service. 
	 */
	@ManyToOne
	public Service parent;
	
	/**
	 * All sessions of a session. This list is created
	 * only if the service is a parent service.
	 */
	@OneToMany(mappedBy="parent")
	public List<Service> sessions;
	
	/**
	 * Owner of the service.
	 */
	@ManyToOne
	public User owner;
	
	/**
	 * Number of participants, which makes a service
	 * applicable.
	 */
	public int minNumberOfParticipants = 1;
	
	/**
	 * Maximum number of attendees count.
	 */
	public int maxNumberOfParticipants;
	
	/**
	 * Geolocation of the service; if it's not virtual.
	 */
	public String location;
	
	public boolean isVirtual;
	
	/**
	 * Requesters or offerers of the service. Users in
	 * this set are waiting for the owner's approval.
	 */
	@ManyToMany(cascade=CascadeType.ALL) 
	public Set<User> participantCandidates = new HashSet();
	
	/**
	 * Requesters or offerers of the service. Users in
	 * this set are approved by the owner.
	 */
	/*@ManyToMany(cascade=CascadeType.ALL) 
	Set<User> participants = new HashSet<User>();
	*/
	/**
	 * Status of the service.
	 * 
	 * @TODO verify service statuses.
	 */
	public enum Status {
        //WAITING, HANDSHAKED
		//WAITING_APPROVAL, ACCEPTED, REJECTED, STARTED, CANCELLED, DONE, UNKNOWN, IGNORED
		CREATED, CANCELLED, HANDSHAKED, COMPLETED
    }
	
	public Status status;
	
	/**
	 * Given a user, add him/her to the session.
	 * 
	 * @param user	user that will be added to the session.
	 * @TODO the method should be implemented.
	 */
	public void addUser(User user)
	{
		this.participantCandidates.add(user);
		this.save();
	}
	
	/**
	 * Given a user of the current session, revokes him/her from
	 * the session. 
	 * 
	 * @param user 		user that will be revoked from the session.
	 * @TODO the method should be implemented. 
	 */
	public void revokeUser(User user)
	{	
	}
	
	/**
	 * Returns the duration of the current session or the
	 * sum of all sessions' durations.
	 * 
	 * @return	duration of the session(s).
	 */
	public double getDuration() {
		double duration = 0.0;
		
		if (! this.isSession()) {
			for (Service session : this.sessions) {
				duration += session.getDuration();
			}
		} else if (null != this.startsAt && null != this.endsAt) {
			duration = (this.endsAt.getTime() - this.startsAt.getTime()) / 3600000;
		}
		
		return duration;
	}
	
	public boolean isSession() {
		return (null != this.parent) || (null == this.parent && 0 == this.sessions.size());
	}
	
	public Service getParent() {
		return (null != this.parent) ? this.parent : this;
	}
	
	public CreditType getCredits() {
		return CreditManager.getService(this);
	}
	
	public List<Tag> getTags()
    {
        return this.tags;
    }
	
	public String getDescription()
    {
        return this.description;
    }
	
	public String getTitle()
    {
        return this.title;
    }
	
	/**
	 * Method used to create sessions for a parent service.
	 * 
	 * @TODO build the algorithm and refine the method
	 * signature.
	 */
	protected abstract void createSessions();
	
	/**
	 * Method used to updates sessions for a parent service.
	 * Used to change information for all child services at
	 * once.
	 * 
	 * @TODO build the algorithm and refine the method
	 * signature.
	 */
	protected abstract void updateSessions();
	
	/**
	 * Method used to delete all sessions of a parent service.
	 * 
	 * @TODO for each session of the service, remove the child
	 * service and its relations.
	 */
	protected abstract void deleteSessions();

}
