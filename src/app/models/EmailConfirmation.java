package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class EmailConfirmation extends Model
{
    @Required
    @OneToOne
    public User user;

    @Required
    public String code;
}
