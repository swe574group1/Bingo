package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Handshake extends Model
{
    public enum Status {
        WAITING_APPROVAL, ACCEPTED, REJECTED, UNKNOWN
    }


    @ManyToOne
    public Offer offer;


    @ManyToOne
    public Request request;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;

    // @Required
    // @Temporal(TemporalType.TIMESTAMP)
    // public Date actualStartDate;

    // @Required
    // @Temporal(TemporalType.TIMESTAMP)
    // public Date actualEndDate;

    // @Required
    // @Enumerated(EnumType.STRING)
    // public Status status;

    public Handshake(Offer offerItem, User requester) {
	this.offer = offerItem;
	this.request = new Request(requester);
	this.creationDate = new Date();
    }
}
