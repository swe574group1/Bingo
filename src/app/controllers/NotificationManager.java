package controllers;

import java.util.List;

import javax.persistence.Query;

import play.data.validation.Required;
import play.db.jpa.JPA;

import models.Handshake;
import models.Notification;
import models.User;

public class NotificationManager {
	
	public void AddNewNotification(User user, String message, String link){
		Notification notification = new Notification();
		notification.user = user;
		notification.message = message;
		notification.link = link;
		notification.save();
	}
	
	public List<Notification> GetNotifications(Long user_id){
		Query query = JPA.em().createQuery("from " + Notification.class.getName() + " where user_id=" + user_id);
		List<Notification> notifications = query.getResultList();
		return notifications;
	}
	
}
