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

public class OnlineFormFacade {
	private static OnlineFormFacade singleton;
	
	private DataAccess dao;
	
	private OnlineFormFacade() throws NamingException, SQLException {
		this.dao = DataAccess.getInstance();
	}
	
	public static OnlineFormFacade getInstance() throws NamingException, SQLException {
	
		if(singleton == null)
			singleton = new OnlineFormFacade();
		return singleton;
	}
	
	
	public OnlineForm[] getOnlineForms() throws SQLException {
		//get the connection from the FormDataAccess singleton object
		Connection con = dao.getConnection();
		
		//Execute the query
		PreparedStatement stmt = con.prepareStatement("SELECT * from onlineforms");
		ResultSet rs = stmt.executeQuery();
		
		//Build the array of SpecialPermissionForm objects
		OnlineForm[] formArray = new OnlineForm[100];
		int count = 0;
		while (rs.next()) {
			int theId = rs.getInt(1);
			//String theStudentName = rs.getString(2);
			String theCourseDept = rs.getString(2);
			int theCourseNumber = rs.getInt(3);
			String theSemester = rs.getString(4);
			int theYear = rs.getInt(5);
			String theTitle = rs.getString(6);
			OnlineForm form = new OnlineForm(theId, theCourseDept, theCourseNumber, theSemester, theYear, theTitle);
			formArray[count] = form;
			count++;
		}
		
		if(count > 0) {
			formArray = Arrays.copyOf(formArray, count);
			return formArray;
		}
		
		else
			return null;
	}//end getOnlineForms
}//end main class

		