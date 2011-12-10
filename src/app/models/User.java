package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model
 {
     public int INITIAL_BALANCE = 10;
     public int INITIAL_REPUTATION = 1;

     @Required
     public String email;

     @Required
     public String password;

     // @Required
     // public String nickname;

     @Required
     public String fullname;

     @Required
     public Boolean isAdmin;

     @Required
     @Temporal(TemporalType.TIMESTAMP)
     public Date registrationDate;

     @Required
     public String address;

     // @Required
     // public String phone;

     // @OneToOne
     // public UploadedFile avatar;

     @Required
     public Integer balance;

     @Required
     public Integer reputation;

     // @Required
     // @Temporal(TemporalType.DATE)
     // public Date birthday;

     // @Required
     // public String job;

     // @Required
     // public City city;

     // @Required
     // public County county;

     // @Required
     // @Enumerated(EnumType.STRING)
     // public Status status;

     public static List<User> getNewUsers(int maxUsers) {
	 return find("order by registrationDate DESC").fetch(maxUsers);
     }
     
     public static User connect(String email, String password) {
	 return find("byEmailAndPassword", email, password).first();
     }

     public User() {
	 this.registrationDate = new Date();
	 this.isAdmin = false;
	 this.balance = INITIAL_BALANCE;
	 this.reputation = INITIAL_REPUTATION;
     }	 

     public Blob photo;
}
