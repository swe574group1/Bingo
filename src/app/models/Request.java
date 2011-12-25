package models;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.Offer.Status;

import play.data.validation.Required;
import play.db.jpa.Model;
import service.Matchable;

@Entity
public class Request extends Model implements Matchable
{
    public enum Status {
        WAITING, HANDSHAKED
    }

    @Required
    public String title;

    @Required
    @Lob
    public String description;

    @Required
    @OneToMany(mappedBy="request", cascade=CascadeType.ALL)
    public List<Tag> tags;
    
    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    @Required
    public Integer credit;
    
    @ManyToOne
    public User user;

    public Boolean isFinalized;

    // @Required
    @Enumerated(EnumType.STRING)
    public Status status;

    @ManyToOne
    public City city;
    
    @ManyToOne
    public County county;
    
    @ManyToOne
    public District district;
    
    public String city_name;
    
    public String county_name;
    
    public String district_name;
    
    public Boolean is_all_days;  
    public Boolean is_all_hours;  
    public Boolean is_rec_monday;    
    public Boolean is_rec_tuesday;
    public Boolean is_rec_wednesday;
    public Boolean is_rec_thursday;
    public Boolean is_rec_friday;
    public Boolean is_rec_saturday;
    public Boolean is_rec_sunday;
       
    public Request(User user) {
	this.user = user;
    }

    public Request() {
	
    }

    @Override
    public List<Tag> getTags()
    {
        return tags;
    }

    public Request(User user, Offer offer) {
	this.user = user;
	this.title = offer.title;
	this.description = offer.description;
	this.endDate = offer.endDate;
    }

}
