package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * Comment is the model class that represents an abstract
 * version of a comment. The class is marked as an entity;
 * because Play Framework 1.2.4 supports "concrete parent
 * classes" for model inheritance.
 * <p>
 * Possible comments are:
 * <ul>
 * <li>Comments made for offers
 * <li>Comments made for requests
 * <li>Comments made for handshakes
 * </ul>
 * <p>
 * The class was originally created by last year's "Let It Bee"
 * group members.
 * 
 * @author	Onur Yaman  <onuryaman@gmail.com>
 * @version 2.0
 * @since	1.0
 */
@Entity
public abstract class Comment extends Model
{
	/**
	 * The content of the comment. There is no limit for
	 * the size of the text.
	 */
    public String text;
    
    /**
     * The creation date and time of the comment.
     */
    public Date date;

    /**
     * The user who has made the comment.
     */
    @ManyToOne
    public User user;
}
