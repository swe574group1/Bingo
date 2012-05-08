package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import javax.persistence.Query;

import models.Comment;
import models.Offer;
import models.OfferComment;
import models.PrivateMessage;
import models.Request;
import models.Tag;
import models.User;
import models.Handshake;
import models.CreditType;
import service.CreditManager;
import service.MatchService;
import service.Utils;

import play.db.jpa.JPA;

/**
 * PrivateMessages is the controller class that is responsible
 * of handling HTTP requests for private messages.
 * <p>
 * PrivateMessages includes the following features:
 * <ul>
 * <li>Listing inbox messages
 * <li>Listing outbox messages
 * <li>Reading a specific message
 * <li>Replying to a specific message
 * </ul>
 * 
 * @author	Onur Yaman  <onuryaman@gmail.com>
 * @version 1.0
 */
public class PrivateMessages extends BaseController {
	
	/**
	 * Lists inbox messages.
	 * 
	 * @see		User
	 * @see		#render
	 * @since	1.0
	 */
    public static void inbox() {
    	// get the current connected user.
    	User user = getConnectedUser();

    	// render the corresponding template with the inbox
    	// messages of the user.
    	render(user.inbox);
    }
    
    /**
	 * Lists outbox messages.
	 * 
	 * @see		User
	 * @see		#render
	 * @since	1.0
	 */
    public static void outbox() {
    	// get the current connected user.
    	User user = getConnectedUser();

    	// render the corresponding template with the outbox
    	// messages of the user.
    	render(user.outbox);
    }
    
    public static void send(User receiver) {
    	// get the current connected user.
    	User user = getConnectedUser();
    	
    	// fetch the message.
    	String message = request.params.get("text");
    	
    	// send the message.
    	PrivateMessage.send(user, receiver, message);
    	
    	// redirect to outbox.
    	outbox();
    }
    
}
