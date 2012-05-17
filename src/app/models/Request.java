package models;

import java.util.ArrayList;
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
    public Double duration;
    
    @Required
    @Lob
    public String description;

    @Required
    @OneToMany(mappedBy="request", cascade=CascadeType.ALL)
    public List<Tag> tags;
    
    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    public Double creditOffer;
    public Double creditRequest;

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
    
    public Boolean reoccure;       
    public Boolean is_rec_monday;    
    public Boolean is_rec_tuesday;
    public Boolean is_rec_wednesday;
    public Boolean is_rec_thursday;
    public Boolean is_rec_friday;
    public Boolean is_rec_saturday;
    public Boolean is_rec_sunday;
    
    public Boolean is_all_hours; 
    public Boolean is_virtual; 
       
    public String reocc_start_hour;    
    public String reocc_end_hour;
    public Integer reocc_start_hour_val;
    public Integer reocc_end_hour_val;
    
    @OneToMany(mappedBy="request")
    public List<RequestComment> comments;
    
    public Request(User user) {
    	this.tags = new ArrayList<Tag>();
    	this.user = user;
    }

    public Request() {
    	this.tags = new ArrayList<Tag>();
    	this.status = Status.WAITING;
    }

    @Override
    public List<Tag> getTags()
    {
        return tags;
    }
    
    @Override
    public String getDescription()
    {
        return description;
    }    

    @Override
    public String getTitle()
    {
        return title;
    }    
    
    public Request(User user, Offer offer) {
    	this.tags = new ArrayList<Tag>();
		this.user = user;
		this.title = offer.title;
		this.duration = offer.duration;
		this.description = offer.description;
		this.endDate = offer.endDate;
		this.status = Status.WAITING;
    }

}
