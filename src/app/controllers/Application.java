package controllers;

import java.util.List;

import models.Handshake;
import models.Handshake.Status;
import models.User;

public class Application extends BaseController
{   
    public static void index()
	{
	    int recentUserCount = 5;
	    int recentHandshakeCount = 5;
	    List<User> newUsers = User.getNewUsers(recentUserCount);
	    List<Handshake> newHandshakes = Handshake.find("status!='WAITING_APPROVAL' or status !='DONE' order by creationDate desc").fetch(recentHandshakeCount);
	    render(newUsers, newHandshakes);
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
