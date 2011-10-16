package controllers;

import models.User;
import play.mvc.Controller;

public class Application extends Controller
{
	public static void index()
	{
		// Create a new user and save it
		new User("bob@gmail.com", "secret", "Bob").save();

		// Retrieve the user with e-mail address bob@gmail.com
		User bob = User.find("byEmail", "bob@gmail.com").first();

		render(bob);
	}
}