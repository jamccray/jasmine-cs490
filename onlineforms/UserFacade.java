package com.cs490;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Calendar;

public class UserFacade {
	private static UserFacade singleton;
	
	private DataAccess dao;
	
	private UserFacade() throws NamingException, SQLException {
		this.dao = DataAccess.getInstance();
	}
	
	public static UserFacade getInstance() throws NamingException, SQLException {
	
		if(singleton == null)
			singleton = new UserFacade();
		return singleton;
	}
	
	public OnlineUser[] getOnlineUsers() throws SQLException {
		//get the connection from the FormDataAccess singleton object
		Connection con = dao.getConnection();
		
		//Execute the query
		PreparedStatement stmt = con.prepareStatement("SELECT * from onlineforms_user");
		ResultSet rs = stmt.executeQuery();
		
		//Build the array of SpecialPermissionForm objects
		OnlineUser[] userArray = new OnlineUser[100];
		int count = 0;
		while (rs.next()) {
			int theId = rs.getInt(1);
			String theUserName = rs.getString(2);
			String theUserFirstName = rs.getString(3);
			String theUserLastName = rs.getString(4);
			String theUserEmail = rs.getString(5);
			String theUserPassword = rs.getString(6);
			Boolean theInactive = rs.getBoolean(7);
			OnlineUser users = new OnlineUser(theId, theUserName, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, theInactive);
			userArray[count] = users;
			count++;
		}
		
		if(count > 0) {
			userArray = Arrays.copyOf(userArray, count);
			return userArray;
		}
		
		else
			return null;
	}//end getOnlineForms	

	public OnlineUser[] createUser(OnlineUser theUserToAdd) throws SQLException, ClassNotFoundException {
		System.out.println("in UserFacade.createuser");
		
		//get the connection from the FormDataAccess singleton object
		Connection con = dao.getConnection();
		
		String newUsername = theUserToAdd.getUsername();
		String newUserFirstName = theUserToAdd.getFirstName();
		String newUserLastName = theUserToAdd.getLastName();
		String newUserEmail = theUserToAdd.getEmail();
		String newUserPassword = theUserToAdd.getPassword();
		//Set Inactive flag = false (initially set to "true" (in OnlineUser.java))
		theUserToAdd.setInactive();
		Boolean newUserInactive = theUserToAdd.getInactive();

		
		//Study the INSERT statement
		PreparedStatement createStmt = con.prepareStatement("INSERT INTO onlineforms_user(userName, userFirstName, userLastName, userEmail, userPassword, inactive) VALUES (?, ?, ?, ?, ?, ?)");
		createStmt.setString(1, newUsername);
		createStmt.setString(2, newUserFirstName);
		createStmt.setString(3, newUserLastName);
		createStmt.setString(4, newUserEmail);
		createStmt.setString(5, newUserPassword);
		createStmt.setBoolean(6, newUserInactive);		
		int res = createStmt.executeUpdate();
		
		PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE userName = ? AND userFirstName = ? AND userLastName = ? AND userEmail = ? AND userPassword = ? AND inactive = ?");
		retrieveStmt.setString(1, newUsername);
		retrieveStmt.setString(2, newUserFirstName);
		retrieveStmt.setString(3, newUserLastName);
		retrieveStmt.setString(4, newUserEmail);
		retrieveStmt.setString(5, newUserPassword);
		retrieveStmt.setBoolean(6, newUserInactive);
		ResultSet rs = retrieveStmt.executeQuery();
		
		String result = "";
		int count = 0;
		int MAX = 100;
		OnlineUser[] OnlineUserArray = new OnlineUser[MAX];
					
		while(rs.next()) {
			int theId = rs.getInt("id");
			String theUsername = rs.getString("userName");
			String theUserFirstName = rs.getString("userFirstName");
			String theUserLastName = rs.getString("userLastName");
			String theUserEmail = rs.getString("userEmail");
			String theUserPassword = rs.getString("userPassword");
			Boolean theUserInactive = rs.getBoolean("inactive");
			theUserToAdd = new OnlineUser(theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, theUserInactive);
			System.out.println(theUserToAdd);
			OnlineUserArray[count] = theUserToAdd;
			count++;
		}//end while
					
		//trim the array, which reduces the size down to actual number of SpecialPermissionForms
		if(count > 0) {
			OnlineUserArray = Arrays.copyOf(OnlineUserArray, count);
			return OnlineUserArray;
		}
		else
			return null;
	}//end create user
	
	
	public OnlineUser[] getUserByUserName(String theUserName) throws SQLException, ClassNotFoundException, NamingException {
		//get the connection from the FormDataAccess singleton object
		Connection con = dao.getConnection();
		
		//Execute the query
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE userName = ?");
		stmt.setString(1, theUserName);
		ResultSet rs = stmt.executeQuery();
		
		//Build the array of SpecialPermissionForm objects
		OnlineUser[] OnlineUserArray = new OnlineUser[100];
		int count = 0;
		while (rs.next()) {
			int theId = rs.getInt("id");
			String theUsername = rs.getString("userName");
			String theUserFirstName = rs.getString("userFirstName");
			String theUserLastName = rs.getString("userLastName");
			String theUserEmail = rs.getString("userEmail");
			String theUserPassword = rs.getString("userPassword");
			Boolean theUserInactive = rs.getBoolean("inactive");
			OnlineUser user = new OnlineUser(theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, theUserInactive);
			System.out.println(user);
			OnlineUserArray[count] = user;
			count++;			
		}
		
		if(count > 0) {
			OnlineUserArray = Arrays.copyOf(OnlineUserArray, count);
			return OnlineUserArray;
		}
		
		else
			return null;
	}//end getUserByName
	
	public OnlineUser[] getUserById(int theId) throws SQLException, ClassNotFoundException, NamingException {
		//get the connection from the FormDataAccess singleton object
		Connection con = dao.getConnection();
		
		//Execute the query
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE id = ?");
		stmt.setInt(1, theId);
		ResultSet rs = stmt.executeQuery();
		
		//Build the array of SpecialPermissionForm objects
		OnlineUser[] OnlineUserArray = new OnlineUser[100];
		int count = 0;
		while (rs.next()) {
			int theId2 = rs.getInt(1);
			String theUsername = rs.getString("userName");
			String theUserFirstName = rs.getString("userFirstName");
			String theUserLastName = rs.getString("userLastName");
			String theUserEmail = rs.getString("userEmail");
			String theUserPassword = rs.getString("userPassword");
			Boolean theUserInactive = rs.getBoolean("inactive");			
			OnlineUser users = new OnlineUser(theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, theUserInactive);
			OnlineUserArray[count] = users;
			count++;
		}

		// If you use println it goes to the console without running tomcat
		System.out.println("getUserById: theInactive: " + OnlineUserArray[0] + "\n");

		if(count > 0) {
			OnlineUserArray = Arrays.copyOf(OnlineUserArray, count);
			return OnlineUserArray;
		}
		
		else
			return null;	
	}//end getUserbyId 
	
	public OnlineUser[] updateUser(OnlineUser theUserObject) 
		throws SQLException, ClassNotFoundException, NamingException {

			// Get the connection from the IgredinetDataAccess singleton
			Connection con = dao.getConnection();

			int theId = theUserObject.getId();
			String theUsername = theUserObject.getUsername();
			String theUserFirstName = theUserObject.getFirstName();
			String theUserLastName = theUserObject.getLastName();
			String theUserEmail = theUserObject.getEmail();
			String theUserPassword = theUserObject.getPassword();	
			Boolean theInactive = theUserObject.getInactive();

			// Create prepared statement
			PreparedStatement createStmt = con.prepareStatement("UPDATE onlineforms_user SET userName = ?, userFirstName = ?, userLastName = ?, userEmail = ?, userPassword = ?, inactive = ? WHERE id = ?");

			createStmt.setString(1, theUsername);
			createStmt.setString(2, theUserFirstName);	
			createStmt.setString(3, theUserLastName);
			createStmt.setString(4, theUserEmail);
			createStmt.setString(5, theUserPassword);
			createStmt.setBoolean(6, theInactive);
			createStmt.setInt(7, theId);									

			// The executeUpdate returns the numner of rows affected, we expect 1
			int res = createStmt.executeUpdate();

			// for debugging purposes print result to tomcat console
			System.out.println("Result is: " + res);
				
			// If insert was successful retrieve the new ingredient to retrieve id
			if (res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE id=?");
			retrieveStmt.setInt(1, theId);
			//retrieveStmt.setString(2,newCategory);	
			ResultSet rs = retrieveStmt.executeQuery();

			String result ="";
			//Build the array of SpecialPermissionForm objects
			OnlineUser[] OnlineUserArray = new OnlineUser[100];
			int count = 0;
			while (rs.next()) {
				int theId2 = rs.getInt("id");
				String updatedUsername = rs.getString("userName");
				String updatedUserFirstName = rs.getString("userFirstName");
				String updatedUserLastName = rs.getString("userLastName");
				String updatedUserEmail = rs.getString("userEmail");
				String updatedUserPassword = rs.getString("userPassword");	
				Boolean updatedInactive = rs.getBoolean("inactive");		
				OnlineUser users = new OnlineUser(theId2, updatedUsername, updatedUserFirstName, updatedUserLastName, updatedUserEmail, updatedUserPassword, updatedInactive);
				System.out.println(users);
				OnlineUserArray[count] = users;
				count++;
			}

			if (count > 0) {
				// this is to make sure we copied at least 1 item in the array
				OnlineUserArray = Arrays.copyOf(OnlineUserArray, count);
				return OnlineUserArray;
			} else {
				// this means we didn't manage to insert data into array
				return null;
			}
		} else {
			// this means we didn't manage to insert data into database
			return null;
		}				
	}//end updateUser class	

	public OnlineUser[] deleteUser(int theId) 
		throws SQLException, ClassNotFoundException, NamingException {

			// Get the connection from the IgredinetDataAccess singleton
			Connection con = dao.getConnection();
				
			//Execute the query
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE id = ?");
			stmt.setInt(1, theId);
			ResultSet rs = stmt.executeQuery();
			
			//Build the array of user objects
			OnlineUser[] OnlineUserArray = new OnlineUser[100];
			int count = 0;
			while (rs.next()) {
				int theId2 = rs.getInt("id");
				String theUsername = rs.getString("userName");
				String theUserFirstName = rs.getString("userFirstName");
				String theUserLastName = rs.getString("userLastName");
				String theUserEmail = rs.getString("userEmail");
				String theUserPassword = rs.getString("userPassword");
				Boolean theUserInactive = rs.getBoolean("inactive");			
				OnlineUser users = new OnlineUser(theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, theUserInactive);
				OnlineUserArray[count] = users;
				count++;
			}

			//Flip the user's inactivity flag to true bc we don't want to actually delete the user
			OnlineUserArray[0].setInactive(); //should now be true.
			System.out.println("Inactive status is: " + OnlineUserArray[0].getInactive()); //Just a test.. printing the inactive status
			Boolean theInactive = OnlineUserArray[0].getInactive();

			// Create prepared statement
			PreparedStatement createStmt = con.prepareStatement("UPDATE onlineforms_user SET inactive = ? WHERE id = ?");
			createStmt.setBoolean(1, theInactive);
			createStmt.setInt(2, theId);
			
			// The executeUpdate returns the number of rows affected, we expect 1
			int res = createStmt.executeUpdate();

			// for debugging purposes print result to tomcat console
			System.out.println("Result is: " + res);
				
			// If insert was successful retrieve the new user to retrieve id
			if (res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("SELECT * FROM onlineforms_user WHERE id = ?");
			retrieveStmt.setInt(1, theId);
			ResultSet res2 = retrieveStmt.executeQuery();
			
			// for debugging purposes print result to tomcat console
			System.out.println("Result 2 is: " + res2);
			}
			
			if (count > 0) {
				// this is to make sure we copied at least 1 item in the array
				OnlineUserArray = Arrays.copyOf(OnlineUserArray, count);
				return OnlineUserArray;
			} else {
				// this means we didn't manage to insert data into database
				return null;
			}				
	}//end updateUser class	
	
	/**
		Authentication method for user.
	*/
	public int authenticateUser(String theUsername, String thePassword) throws SQLException, ClassNotFoundException, NamingException{
		
		// Get the connection from the DataAccess singleton
		Connection con = dao.getConnection();
			
		//Execute the query
		PreparedStatement stmt = con.prepareStatement("SELECT userName, userPassword FROM onlineforms_user WHERE userName = ? AND userPassword = ?");
		stmt.setString(1, theUsername);
		stmt.setString(2, thePassword);
		ResultSet rs = stmt.executeQuery();
		
		//System.out.println("In authenticateUser(UserFacade) Authentication Result is: " + rs);
		
		String userName = "";
		String passWord = "";
		
		while(rs.next()){
		
			userName = rs.getString(1);
			passWord = rs.getString(2);
		}
	
		if(rs != null){
			
			if(userName.equals(theUsername) && passWord.equals(thePassword)){
				Calendar calender = Calendar.getInstance();
				java.sql.Timestamp timeStampObject = new java.sql.Timestamp(calender.getTime().getTime());
				
				String sess_id = userName + timeStampObject.toString();
				
				PreparedStatement createStmt = con.prepareStatement("INSERT INTO authenticated_Session(userName, userPassword, TS, session_id) values (?,?,?,?)");
				createStmt.setString(1, userName);
				createStmt.setString(2, passWord);
				createStmt.setTimestamp(3, timeStampObject);
				createStmt.setString(4, sess_id);
				
				int res2 = createStmt.executeUpdate();
				
				System.out.println("Result 2 is: " + res2);
				
				return 1; //case that the test passes
			}else{
				
				return 0;// password and/or username don't match
			}
		}
		else{
			
			//didn't retrieve a row from the database meaning either the username and/or password didn't match
			return 0; // case that the test failed
		}
	}//end authenticateUser class
	
	public int deauthenticateUser(int theId) throws SQLException, ClassNotFoundException, NamingException
	{
			// Get the connection from the IgredinetDataAccess singleton
			Connection con = dao.getConnection();
				
			//check if they're logged in
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM authenticated_Session WHERE id = ?");
			stmt.setInt(1, theId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs != null)
				{
					int theId2 = theId;
					PreparedStatement pstmt = con.prepareStatement("DELETE FROM authenticated_Session WHERE id = ?");
					pstmt.setInt(1, theId2);
					ResultSet rs2 = pstmt.executeQuery();
					
					return 1;
						
				}
			else //they weren't logged in
				{return 0;}
			
	}
}//end UserFacade class