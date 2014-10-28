package com.cs490;

import java.util.*;

public class OnlineUser {
	// Default Constructor
	private int id;
	private String userName;
	private String user_firstName;
	private String user_lastName;
	private String user_email;
	public String user_id;
	private String user_password;
	private Boolean user_inactive = true;
	
	public OnlineUser(int id, String userName, String user_firstName, String user_lastName, String user_email, String user_id, String user_password, Boolean user_inactive)
	{
		setId(id);
		setUsername(userName);
		setFirstName(user_firstName);
		setLastName(user_lastName);
		setEmail(user_email);
		setUserId(user_id);
		setPassword(user_password);
		this.user_inactive = user_inactive;
	}
	
	public OnlineUser(String userName, String user_firstName, String user_lastName, String user_email, String user_id, String user_password)
	{
		setId(0);
		setUsername(userName);
		setFirstName(user_firstName);
		setLastName(user_lastName);
		setEmail(user_email);
		setUserId(user_id);
		setPassword(user_password);
		this.user_inactive = user_inactive;
		
	}
	
	public String toString()
	{
		String result = "id: " + id + " username: " + userName + " user firstname: " + user_firstName + "user lastname: " + user_lastName + "user email: " + user_email + "user id: " + user_id +"user password: " + user_password + "user inactive: " + user_inactive;
		return result;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) throws InputMismatchException{
		
		try{
			
			if(id < 0){
				
				System.out.println("Error - Please enter a non-negative integer for ID.");
				
			}else{
			
				this.id = id;
			}
		}catch(InputMismatchException e){
		
			System.out.println("Error - Value to be set was not a Integer. Please enter a Integer (i.e. 1,2,3).");
			
		}
	}
	
	public String getUsername()
	{
		return userName;
	}
	
	public void setUsername(String userName) throws InputMismatchException{
	
		try{
			
			if(userName.equals(null) || userName == ""){
				
				System.out.println("Error - Invalid input for Username. ");
				
			}else{
			
				this.userName = userName;
			}
		}catch(InputMismatchException e){
			
			System.out.println("Error - Value to be set was not a String. Please enter a String (i.e. Carl_name, Sam_here)");
		}	
	}
	
	public String getFirstName()
	{
		return user_firstName;
	}
	
	public void setFirstName(String user_firstName) throws InputMismatchException{
	
		try{
		
			if(user_firstName.equals(null) || user_firstName == ""){
			
				System.out.println("Error - invalid input for First Name.");
			}else{
				
				this.user_firstName = user_firstName;
			}
		}catch(InputMismatchException e){
		
			System.out.println("Error - Value to be set was not a String");
		}
			
		
	}
	
	public String getLastName()
	{
		return user_lastName;
	}
	
	public void setLastName(String user_lastName) throws InputMismatchException{
	
		try{
		
			if(user_lastName.equals(null) || user_lastName == ""){
			
				System.out.println("Error - Invalid input for Last Name.");
			
			}else{
			
				this.user_lastName = user_lastName;
			}
		}catch(InputMismatchException e){
		
			System.out.println("Error - Value to be set was not a String");
			
		}
	
		
	}
	
	public String getEmail()
	{
		return user_email;
	}
	
	public void setEmail(String user_email) throws InputMismatchException{
	
		try{
		
			if(user_email.equals(null) || user_email == "") {
			
				System.out.println("Error - Invalid input for Email.");
			
			}else{	
				
				this.user_email = user_email;
			
			}
		}catch(InputMismatchException e){
			
			System.out.println("Error - Value to be set was not a String");
		}
	}
	
	public String getUserId() 
	{
		return user_id;
	}
	
	public void setUserId(String user_id) throws InputMismatchException{
		
		try{
			
			if(user_id.length() < 7 || user_id.length() > 7){
				
				System.out.println("Error - Please enter a non-negative integer for User ID.");
				
			}else{
			
				this.user_id = user_id;
			}
		}catch(InputMismatchException e){
		
			System.out.println("Error - Value to be set was not a String.");
			
		}
	}
	
	public String getPassword()
	{
		return user_password;
	}
	
	public void setPassword(String user_password) throws InputMismatchException{
		
			try{
			
				if(user_password.equals(null) || user_password == ""){
					
					System.out.println("Error - Invalid input for Password.");
				
				}else{
					
					this.user_password = user_password;
				
				}
			}catch(InputMismatchException e){
				
				System.out.println("Error - Value to be set was not a String");
			}
			
	
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
	