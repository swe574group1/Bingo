package controllers;

import java.util.Date;

import models.City;
import models.County;
import models.User;
import play.mvc.Controller;

public class Application extends Controller
{
	public static void index()
	{
		// Create a new user and save it
		User newUser = new User();
		newUser.email = "bob@gmail.com";
		newUser.password = "secret";
        newUser.nickname = "bob";
		newUser.fullname = "Bob";
		newUser.address = "address";
		//newUser.avatar = new UploadedFile();
		newUser.balance = 0;
		newUser.birthday = new Date();
		newUser.city = new City();
		newUser.county = new County();
		newUser.isAdmin = false;
		newUser.job = "job";
		newUser.phone = "phone";
		newUser.registrationDate = new Date();
		newUser.reputation = 0;
		newUser.status = User.Status.WAITING_CONFIRMATION;
		newUser.save();

		// Retrieve the user with e-mail address bob@gmail.com
		User bob2 = User.find("byEmail", "bob@gmail.com").first();

		render(bob2);
	}
}