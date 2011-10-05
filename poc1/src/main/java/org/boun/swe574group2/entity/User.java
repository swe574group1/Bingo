package org.boun.swe574group2.entity;

import java.util.Date;

public class User extends Entity
{
	private int id;
	private String email;
	private String password;
	private String name;
	private Date dateRegistered;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDateRegistered()
	{
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered)
	{
		this.dateRegistered = dateRegistered;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name
			+ ", dateRegistered=" + dateRegistered + "]";
	}
}
