package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Comment extends Model
{
    public String text;
    public Date date;

    @ManyToOne
    public User user;

    @ManyToOne
    public Handshake handshake;
}
