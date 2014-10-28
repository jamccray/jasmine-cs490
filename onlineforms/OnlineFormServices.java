package com.cs490;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.Arrays;

@Path("online")
public class OnlineFormServices{
	
	//The optional id indicates a path parameter ( a paramenter
	// passed directly in the URL path
	// @PathParam("id") means that the value in the URL that
	// occcupies the spot where /{id} is, will be passed
	// through theId parameter to the method
	@Path("/user/{id}")
	@GET
	@Produces("text/plain")
	public Response getUserById(@PathParam("id") String theId) throws SQLException, ClassNotFoundException, NamingException {
		//Get a reference to the FormFacade singleton object
		UserFacade iFacade = UserFacade.getInstance();
		
		//Convert the path parameter id to an integer
		int intId = 0;
		//Since URL parameters are always strings, convert to INT
		try {
			intId = Integer.parseInt(theId);
		}catch (NumberFormatException ne) {
			intId = 1;
		}
		
		//Call the FormFacade method getFormById to get the form(s)
		//with the matching id
		OnlineUser[] resultArray = iFacade.getUserById(intId);
		
		String valid = iFacade.sessionValid(resultArray[0].user_id);
		System.out.println("In (OnlineFormServices) Valid: " + valid);
		
		if(valid != null){
			//Create a Json string representation of the array of form
			if(resultArray != null) {
				Gson theGsonobj = new Gson();
				String result = theGsonobj.toJson(resultArray);
				
				//Add the JSON string to the response message body.
				ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
				
				//Setting the HTTP status code to 200
				rb.status(200);
				
				//Create and return the Response object
				return rb.build();
				
			}//end if	
			else { //form not found; pick an error status code and send empty response object
				return Response.status(704).build();
			}//end else
		}else{
			return Response.status(401).build();
		}
	}//end getUserById method
	
	//The @QueryParam("name") looks for a section of the URL
	// after the ? that reads name=something. The string in 
	// the URL that occupies the 'something' spot will be passed
	// through the theName parameter into the method
	@Path("/user/un")
	@GET
	@Produces("text/plain")
	public Response getUserByUserName(@QueryParam("username") String theUsername)throws SQLException, ClassNotFoundException, NamingException {
		//Get a reference to the FormFacade singleton object
		UserFacade iFacade = UserFacade.getInstance();
		
		//Call the FormFacade method getFormByName to get the form(s)
		//with the matching name
		OnlineUser[] resultArray = iFacade.getUserByUserName(theUsername);
		System.out.println(resultArray);
		
		String valid = iFacade.sessionValid(resultArray[0].user_id);
		
		if(valid != null){
				//Create a Json string representation of the array of form
				if(resultArray != null) {
					Gson theGsonobj = new Gson();
					String result = theGsonobj.toJson(resultArray);
					
					//Add the JSON string to the response message body.
					ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
					
					//Setting the HTTP status code to 200
					rb.status(200);
					
					//Create and return the Response object
					return rb.build();
					
				}//end if				
				else { //form not found; pick an error status code and send empty response object
					return Response.status(704).build();
				}//end else
			}else{
				return Response.status(401).build();
			}
	}//end getFormByName method

	@Path("/user") //Same path as retrieve, but different http type (POST)
	@POST				  //HTTP request type for posting data to server
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")  //web service expects form data
	//MultivaluedMap is a special data structure for storing form data
	public Response createUser(MultivaluedMap<String,String> userFields) throws SQLException, ClassNotFoundException, NamingException {
			
			String newUserName = userFields.getFirst("Username");
			String newUserFirstName = userFields.getFirst("FirstName");
			String newUserLastName = userFields.getFirst("LastName");
			String newUserEmail = userFields.getFirst("Email");
			String newUserId = userFields.getFirst("StudentID");
			String newUserPassword = userFields.getFirst("Password");
			System.out.println( newUserName + " " + newUserFirstName + " " + newUserLastName + " " + newUserEmail + " " + newUserId + " " + newUserPassword);
			
			//Get a reference to the FormFacade singleton object
			UserFacade iFacade = UserFacade.getInstance();
	
			//Create SpecialPrmissionForm array with new values entered
			OnlineUser theUserToAdd = new OnlineUser(newUserName, newUserFirstName, newUserLastName, newUserEmail, newUserId, newUserPassword);
			System.out.println(theUserToAdd);
			
			//Call the FormFacade method getForms to get the new form(s)
			OnlineUser[] resultArray = iFacade.createUser(theUserToAdd);
			System.out.println(resultArray);
		
			//Create a Json string representation of the array of forms
			if(resultArray != null) {
				Gson theGsonobj = new Gson();
				String result = theGsonobj.toJson(resultArray);
		
				//Add the JSON string to the response message body.
				ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
		
				//Setting the HTTP status code to 200
				rb.status(201);
		
				//Create and return the Response object
				return rb.build();
		
			}//end if
	
			else { //form not found; pick an error status code and send empty response object
				return Response.status(704).build();
			}//end else
	}//end method createUser
		
	@Path("/user")
	@GET
	@Produces("text/plain")
	public Response getOnlineUsers() throws SQLException, ClassNotFoundException, NamingException{
		
		//Get a reference to the FormFacade singleton object
		UserFacade iFacade = UserFacade.getInstance();
		
		//Call the FormFacade method getOnlineForms to get the form(s)
		//with the matching forms
		OnlineUser[] resultArray = iFacade.getOnlineUsers();
		
		String result = "";

		//Create a Json string representation of the array of forms
		if(resultArray != null) {
			Gson theGsonobj = new Gson();
			result = theGsonobj.toJson(resultArray);
			
			System.out.print(result);
			
			//Add the JSON string to the response message body.
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			
			//Setting the HTTP status code to 200
			rb.status(200);
			
			//Create and return the Response object
			return rb.build();
			
		}//end if				
		else { //user not found; pick an error status code and send empty response object
			return Response.status(404).build();
		}//end else			
	}//end method getOnlineUsers 
	
	@Path("/user/{id}")	// same path as retrieve, but different http type (PUT)
	@PUT	// HTTP request type for posting to server
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")	// web service expects form data
	// MultivaluedMap is a special data structure for storing form data
	public Response updateUser(@PathParam("id") String theID, MultivaluedMap<String,String> userFields) 
		throws SQLException, ClassNotFoundException, NamingException {

			// convert the path parameter to an int
			int theId = 0;
			try {
				theId = Integer.parseInt(theID);
			} catch (NumberFormatException ne) {
				// handle error here
			}			

			// First get the form data from the formFields paramater
			// this is whatever you called it when you built the datastring
			// in the ajax function		
			String theUsername = userFields.getFirst("userName");
			String theUserFirstName = userFields.getFirst("userFirstName");
			String theUserLastName = userFields.getFirst("userLastName");
			String theUserEmail = userFields.getFirst("userEmail");
			String theUserId = userFields.getFirst("userId");
			String theUserPassword = userFields.getFirst("userPassword");	
			String theInactive = userFields.getFirst("inactive");				

			// Get a reference to the facade Object
			UserFacade iFacade = UserFacade.getInstance();
			// Create a user instance with the data
			// Boolean.toString(theInactive)
			OnlineUser userObject = new OnlineUser( theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserId, theUserPassword, Boolean.valueOf(theInactive) );

			// Call the IngredientFacade method createIngredient
			// to update the record with the matching id
			OnlineUser[] updateResult = iFacade.updateUser( userObject );		

			String valid = iFacade.sessionValid(updateResult[0].user_id);

			if(valid != null){
					// Create a Json string representation of the array of ingredients
					if (updateResult != null) {
						Gson theGsonObj = new Gson();
						String result = theGsonObj.toJson(updateResult);	
						
						// Add the JSON string to the response message body
						ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
						// Setting the HTTP status code to 200
						rb.status(204);
						// Create and return the Response obbject
						return rb.build();			
					} else {
						// ingredient not inserted
						//Gson theGsonObj = new Gson();
						//Ingredient[] blankIngArray = new Ingredient[1];
						//blankIngArray[0] = new Ingredient(0, "none", "none");
						//String blankResult = theGsonObj.toJson(blankIngArray);
						
						return Response.status(700).build();
					}
			}else{
				return Response.status(401).build();
			}
	}//end updateUser class

	@Path("/user/{id}")	// same path as retrieve, but different http type (PUT)
	@DELETE	// HTTP request type for deleting from server
	//@Produces("text/plain")
	public Response deleteUser(@PathParam("id") String theID) 
		throws SQLException, ClassNotFoundException, NamingException {

			// convert the path parameter to an int
			int theId = 0;
			try {
				theId = Integer.parseInt(theID);
			} catch (NumberFormatException ne) {
				// handle error here
			}			

			// First get the form data from the formFields paramater
			// this is whatever you called it when you built the datastring
			// in the ajax function		
			/*String theUsername = userFields.getFirst("userName");
			String theUserFirstName = userFields.getFirst("userFirstName");
			String theUserLastName = userFields.getFirst("userLastName");
			String theUserEmail = userFields.getFirst("userEmail");
			String theUserPassword = userFields.getFirst("userPassword");	
			String theInactive = userFields.getFirst("inactive");*/				

			// Get a reference to the facade Object
			UserFacade iFacade = UserFacade.getInstance();
			// Create a user instance with the data
			// Boolean.toString(theInactive)
			//OnlineUser userObject = new OnlineUser( theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserPassword, Boolean.valueOf(theInactive) );

			// Call the IngredientFacade method createIngredient
			// to update the record with the matching id
			OnlineUser[] deleteResult = iFacade.deleteUser(theId);			

			String valid = iFacade.sessionValid(deleteResult[0].user_id);
			
			if(valid != null){
					// Create a Json string representation of the array of ingredients
					if (deleteResult != null) {
						Gson theGsonObj = new Gson();
						String result = theGsonObj.toJson(deleteResult);	
						
						// Add the JSON string to the response message body
						ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
						// Setting the HTTP status code to 200
						rb.status(204);
						// Create and return the Response object
						return rb.build();			
					} else {
						// ingredient not inserted
						//Gson theGsonObj = new Gson();
						//Ingredient[] blankIngArray = new Ingredient[1];
						//blankIngArray[0] = new Ingredient(0, "none", "none");
						//String blankResult = theGsonObj.toJson(blankIngArray);
						
						return Response.status(404).build();
					}
			}else{
				return Response.status(401).build();
			}	
	}//end updateUser class	
	
	@Path("/authSession") //Same path as retrieve, but different http type (POST)
	@POST				  //HTTP request type for posting data to server
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")  //web service expects form data
	//MultivaluedMap is a special data structure for storing form data
	public Response authenticateUser(MultivaluedMap<String,String> loginFields) throws SQLException, ClassNotFoundException, NamingException {
			
			/*String newUserName = userFields.getFirst("Username");
			String newUserFirstName = userFields.getFirst("FirstName");
			String newUserLastName = userFields.getFirst("LastName");
			String newUserEmail = userFields.getFirst("Email");
			String newUserPassword = userFields.getFirst("Password");
			System.out.println( newUserName + " " + newUserFirstName + " " + newUserLastName + " " + newUserEmail + " " + newUserPassword);*/
			
			String username = loginFields.getFirst("theUsername");
			String password = loginFields.getFirst("thePassword");
			String sessionID = loginFields.getFirst("theSessionID");
			System.out.println( username + " " + password + " " + sessionID);
			
			//Get a reference to the UserFacade singleton object
			UserFacade iFacade = UserFacade.getInstance();
	
			//Create SpecialPrmissionForm array with new values entered
			//OnlineUser theUserToAdd = new OnlineUser(newUserName, newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
			//System.out.println(theUserToAdd);
			
			//Call the FormFacade method getForms to get the new form(s)
			int result = iFacade.authenticateUser(username, password, sessionID);
			System.out.println("In authenticate User(OnlineFormServices).. result is: " + result);
	
			//Create a Json string representation of the array of forms
			if(result == 1) {
				//Gson theGsonobj = new Gson();
				//String result = theGsonobj.toJson(resultArray);
		
				//Add the JSON string to the response message body.
				ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
		
				//Setting the HTTP status code to 200
				rb.status(201);
		
				//Create and return the Response object
				return rb.build();
		
			}//end if
	
			else { //form not found; pick an error status code and send empty response object
				return Response.status(403).build();
			}//end else
	}//end method authSession
}//end class file