package controllers;

import java.util.*;

import models.*;
import play.data.validation.*;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.*;

public class Application extends BaseController
{   
    public static void index()
	{
	    List<User> newUsers = User.getNewUsers(5);
	    List<Handshake> newHandshakes = Handshake.findAll();
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
