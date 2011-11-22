package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Offer extends Model
{
    @Required
    public String title;

    @Required
    @Lob
    public String description;

    @Required
    @OneToMany(mappedBy="offer", cascade=CascadeType.ALL)
    public List<Tag> tags;

    /* Commenting out to test validation
    @Required
    @Temporal(TemporalType.TIMESTAMP) */
//    public Date startDate;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    // public void setStartDate() {
    // 	this.startDate = new Date();
    // }
	

/*    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    //Commenting these out to test validation
    @Required
    public Boolean async;
*/
    
    @Required
    public Integer credit;

    @ManyToOne
    public User user;

    public UploadedFile image;
    
    // @OneToMany
    // public List<UploadedFile> images;

    // @Required
    // public Boolean allowMultipleAttendees; */

    public Boolean isFinalized;
}
