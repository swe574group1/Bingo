package models;

import java.util.Date;
import java.util.List;

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
public class Offer extends Model
{
    @Required
    public String title;

    @Required
    @Lob
    public String description;

    /*    @Required
    @ManyToMany
    public List<Tag> tags;*/

    //Simple tags field to test validation
    @Required
    public String tags;

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

    // @Required
    // @ManyToOne
    //public User user;

    public String userEmail;

    public UploadedFile image;
    
    // @OneToMany
    // public List<UploadedFile> images;

    // @Required
    // public Boolean allowMultipleAttendees; */
}
