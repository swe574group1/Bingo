package controllers;

import java.util.List;

import models.Handshake;
import models.Handshake.Status;
import models.Notification;
import models.User;

public class Application extends BaseController
{   
    public static void index()
	{
    	int recentUserCount =5;
	    int recentHandshakeCount = 5;
	    List<User> newUsers = User.getNewUsers(recentUserCount);
	    //List<Handshake> newHandshakes = Handshake.find("(status='ACCEPTED' or status='STARTED' or status='DONE') order by creationDate desc").fetch(recentHandshakeCount);
	    
	    //Notifications
	    List<Notification> notifications = null;
	    User user = BaseController.getConnectedUser();
	    NotificationManager nManager = new NotificationManager();
	    List<Notification> unreadNotifications = null;
	    if (user != null){
	    	notifications = nManager.GetNotifications(user.id);
	    	unreadNotifications = nManager.ExtractUnreadNotifications(notifications);
	    }
	    
	    //Render
	   	render(newUsers, notifications, unreadNotifications);
	}

    public static void register() {
		render();
    }

    public static void about() {
	render();
    }

    public static void faq() {
	render();
    }

    public static void contact() {
	render();
    }
    
    public static void termsOfService() {
	render();
    }

    public static void showUserPhoto(Long userId) {
	User user = User.findById(userId);
	renderBinary(user.photo.get());
    }

}
