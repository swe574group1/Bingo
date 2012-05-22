package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;


//@Indexed
@Entity
public class City extends Model
{
	//@Field
    @Required
    public String name;
}
