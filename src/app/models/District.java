package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class District extends Model
{
    @Required
    public String name;

    @Required
    @ManyToOne
    public District district;
}
