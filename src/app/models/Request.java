package models;

import java.util.Date;
import java.util.List;

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

@Entity
public class Request extends Model
{
    @Required
    public String title;

    @Required
    @Lob
    public String description;

    @Required
    @OneToMany(mappedBy="offer", cascade=CascadeType.ALL);
    public List<Tag> tags;
    
    // @Required
    // @Temporal(TemporalType.TIMESTAMP)
    // public Date startDate;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    // @Required
    // public Boolean async;

    // @Required
    // public Integer credit;

    // @Required
    @ManyToOne
    public User user;

    // @OneToMany
    // public List<UploadedFile> images;

    // @Required
    // public Boolean allowMultipleAttendees;

    public Boolean isFinalized;
}
