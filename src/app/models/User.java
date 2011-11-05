package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model
{
    public enum Status {
        WAITING_CONFIRMATION, ACTIVE, PASSIVE
    }

    @Required
	public String email;

    @Required
	public String password;

    @Required
    public String nickname;

    @Required
	public String fullname;

    @Required
	public Boolean isAdmin;

    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date registrationDate;

    @Required
	public String address;

    @Required
	public String phone;

    @OneToOne
	public UploadedFile avatar;

    @Required
    public Integer balance;

    @Required
    public Integer reputation;

    @Required
    @Temporal(TemporalType.DATE)
    public Date birthday;

    @Required
    public String job;

    @Required
    public City city;

    @Required
    public County county;

    @Required
    @Enumerated(EnumType.STRING)
    public Status status;

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
