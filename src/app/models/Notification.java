package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Notification extends Model
{
    @Required
    public String message;
    
    @Required
    public String link;
    
    @Required
    public Boolean isread;
    
    @Required
    @ManyToOne
    public User user;
}
