package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.db.jpa.Blob;
@Entity
public class BadgeEntity  extends Model{
	
    @Required
    public String email;
    
	  @Required
	     public String newbie;
	  
	  @Required
	     public String autobiographer;
	  
	  @Required
	     public String comentater;
	  @Required
	     public String serviceman;
	  
	  @Required
	     public String fivester;
	  
	  @Required
	     public String guru;
	  
	  @Required
	     public String social;
	  
	  @Required
	     public String populist;
	  
	  @Required
	     public String rater;
	  @Required
	     public String beta;
	
	
	
	
	
	
	
	
	
	

}
