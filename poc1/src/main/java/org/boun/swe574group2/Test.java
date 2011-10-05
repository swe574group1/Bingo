package org.boun.swe574group2;

import java.util.Date;
import java.util.List;

import org.boun.swe574group2.dao.DAOFactory;
import org.boun.swe574group2.dao.UserDAO;
import org.boun.swe574group2.entity.User;

public class Test
{
	public static void main(String[] args)
	{
		UserDAO userDAO = DAOFactory.getUserDAO();

		List<User> allUsers = userDAO.loadAll();
		System.out.println(allUsers);

		User existingUser = userDAO.load(2);
		System.out.println(existingUser);

		User newUser = new User();
		newUser.setName("john doe");
		newUser.setEmail("john@doe.com");
		newUser.setPassword("password");
		newUser.setDateRegistered(new Date());
		userDAO.save(newUser);
	}
}
