package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
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
    
	  public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewbie() {
		return newbie;
	}

	public void setNewbie(String newbie) {
		this.newbie = newbie;
	}

	public String getAutobiographer() {
		return autobiographer;
	}

	public void setAutobiographer(String autobiographer) {
		this.autobiographer = autobiographer;
	}

	public String getComentater() {
		return comentater;
	}

	public void setComentater(String comentater) {
		this.comentater = comentater;
	}

	public String getServiceman() {
		return serviceman;
	}

	public void setServiceman(String serviceman) {
		this.serviceman = serviceman;
	}

	public String getFivester() {
		return fivester;
	}

	public void setFivester(String fivester) {
		this.fivester = fivester;
	}

	public String getGuru() {
		return guru;
	}

	public void setGuru(String guru) {
		this.guru = guru;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getPopulist() {
		return populist;
	}

	public void setPopulist(String populist) {
		this.populist = populist;
	}

	public String getRater() {
		return rater;
	}

	public void setRater(String rater) {
		this.rater = rater;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(String serviceCount) {
		this.serviceCount = serviceCount;
	}

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
	  
	  @Required
	    public String serviceCount;
	


	
	
}
