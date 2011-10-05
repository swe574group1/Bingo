package org.boun.swe574group2.dao;

import org.boun.swe574group2.entity.User;

public class UserDAO extends AbstractDAO<User>
{
	public UserDAO()
	{
		super("users", User.class);
	}
}
