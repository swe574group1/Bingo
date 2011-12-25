package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;
import service.Matchable;

@Entity
public class Offer extends Model implements Matchable
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
    @OneToMany(mappedBy="offer", cascade=CascadeType.ALL)
    public List<Tag> tags;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    @Required
    public Integer credit;

    @ManyToOne
    public User user;

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
    
    public Offer(User user) {
	this.user = user;
    }

    public Offer() {
    }

    public Offer(User user, Integer credits, Request request) {
	this.user = user;
	this.credit = credits;
	this.title = request.title;
	this.description = request.description;
	this.endDate = request.endDate;
	this.credit = credits;
    }

    @Override
    public List<Tag> getTags()
    {
        return tags;
    }
}
