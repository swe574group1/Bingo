package models;

import java.util.List;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

public class CountyItem
{
    public String name;
    public Long city_id;
    public Long county_id;
}
