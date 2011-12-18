package models;

import java.util.List;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

public class DistrictItem
{
    public String name;
    public Long county_id;
    public Long district_id;
}
