package models;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    public Offer(User user) {
	this.user = user;
    }

    public Offer() {
    }

    @Override
    public List<Tag> getTags()
    {
        return tags;
    }
}
