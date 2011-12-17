package models;

import java.util.List;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

public class TagCloudItem
{
    public String name;
    public String CssClass;
    public Long count;
    public String hyperlink;
}
