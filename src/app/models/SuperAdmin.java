package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class SuperAdmin extends Model
{
    @Required
    public String username;

    @Required
    public String password;
}
