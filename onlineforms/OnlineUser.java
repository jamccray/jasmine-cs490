package com.cs490;

public class OnlineUser {
	// Default Constructor
	private int id;
	private String userName;
	private String user_firstName;
	private String user_lastName;
	private String user_email;
	private String user_password;
	private Boolean user_inactive = true;
	
	public OnlineUser(int id, String userName, String user_firstName, String user_lastName, String user_email, String user_password, Boolean user_inactive)
	{
		this.id = id;
		this.userName = userName;
		this.user_firstName = user_firstName;
		this.user_lastName = user_lastName;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_inactive = user_inactive;
	}
	
	public OnlineUser(String userName, String user_firstName, String user_lastName, String user_email, String user_password)
	{
		id = 0;
		this.userName = userName;
		this.user_firstName = user_firstName;
		this.user_lastName = user_lastName;
		this.user_email = user_email;
		this.user_password = user_password;
	}
	
	public String toString()
	{
		String result = "id: " + id + " username: " + userName + " user firstname: " + user_firstName + "user lastname: " + user_lastName + "user email: " + user_email + "user password: " + user_password + "user inactive: " + user_inactive;
		return result;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return userName;
	}
	
	public void setUsername(String userName)
	{
		this.userName = userName;
	}
	
	public String getFirstName()
	{
		return user_firstName;
	}
	
	public void setFirstName(String user_firstName)
	{
		this.user_firstName = user_firstName;
	}
	
	public String getLastName()
	{
		return user_lastName;
	}
	
	public void setLastName(String user_lastName)
	{
		this.user_lastName = user_lastName;
	}
	
	public String getEmail()
	{
		return user_email;
	}
	
	public void setEmail(String user_email)
	{
		this.user_email = user_email;
	}
	
	public String getPassword()
	{
		return user_password;
	}
	
	public void setPassword(String user_password)
	{
		this.user_password = user_password;
	}
	
	public Boolean getInactive() 
	{
		return user_inactive;
	}
	
	public void setInactive()
	{
		if(user_inactive)
		{
			user_inactive = false;
		}
		else
		{
			user_inactive = true;
		}
	}	
}
	
	
	