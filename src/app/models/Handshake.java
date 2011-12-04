package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
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

    @OneToOne
    public Offer offer;

    @OneToOne
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
}
