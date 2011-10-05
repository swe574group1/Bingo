package org.boun.swe574group2.dao;

public class DAOFactory
{
	public static UserDAO getUserDAO()
	{
		return new UserDAO();
	}
}
