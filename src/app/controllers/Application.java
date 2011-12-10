package controllers;

import java.util.List;

import models.Handshake;
import models.Handshake.Status;
import models.User;

public class Application extends BaseController
{   
    public static void index()
	{
	    List<User> newUsers = User.getNewUsers(5);
	    List<Handshake> newHandshakes = Handshake.find("status", Status.DONE).fetch();
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
