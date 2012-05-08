package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * PrivateMessage is the model class, which represents
 * a message sent by a user to another user. These
 * messages are private and can be seen only by the
 * sender and the receiver.
 * 
 * @author	Onur Yaman  <onuryaman@gmail.com>
 * @version 1.0
 */
@Entity
public class PrivateMessage extends Model
{
	/**
	 * The content of the message. There is no limit for
	 * the size of the text.
	 */
    public String text;
    
    /**
     * The creation date and time of the message.
     */
    public Date sentAt;

    /**
     * The user who has sent the message.
     */
    @ManyToOne
    public User sender;
    
    /**
     * The user to whom the message was sent.
     */
    @ManyToOne
    public User receiver;
    
    /**
     * Indicates whether or not the receiver has read
     * the message.
     */
    public boolean isRead;
    
    /**
     * Indicated whether or not the sender has deleted
     * the message.
     */
    public boolean isDeletedBySender;
    
    /**
     * Indicated whether or not the receiver has deleted
     * the message.
     */
    public boolean isDeletedByReceiver;
    
    public PrivateMessage() {
    	// set the default values.
    	this.isRead = false;
    	this.isDeletedByReceiver = false;
    	this.isDeletedBySender = false;
    	this.sentAt = new Date();
    }
    
    /**
     * Given a user of the system, analyzes each message
     * of the user's inbox for counting unread messages
     * and returns the result.
     * <p>
     * The operation may be optimized by writing a complete
     * SQL query; but that's not needed for now.
     * 
     * @see			User
     * @param user 	the owner of the inbox
     * @return 		count of unread messages in the inbox.
     * @since		1.0
     */
    public static int getUnreadMessagesCount(User user) {
    	// initialize the unread messages count.
    	int unreadMessagesCount = 0;
    	
    	// for each received messages of the user;
    	for (PrivateMessage pm : user.inbox) {
    		// if the message is unread;
    		if (! pm.isRead) {
    			// increment the counter.
    			unreadMessagesCount++;
    		}
    	}
    	
    	return unreadMessagesCount;
    }
    
    /**
     * Given the message, sender and receiver users; creates a
     * new private message instance and saves it to the database.
     * This is actually sending a private message from a user
     * to another.
     * 
     * @param sender	sender of the private message.
     * @param receiver	receiver of the private message.
     * @param message	content of the private message.
     * @since			1.0
     */
    public static void send(User sender, User receiver, String message) {
    	// build the private message instance.
    	PrivateMessage pm = new PrivateMessage();
    	pm.text = message;
    	pm.receiver = receiver;
    	pm.sender = sender;
    	
    	// save the private message.
    	pm.save();
    }
}
