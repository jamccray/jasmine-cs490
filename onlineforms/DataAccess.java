package com.cs490;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DataAccess {
	private static DataAccess singleton;
	
	private DataSource dataSource;
	
	private DataAccess() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		this.dataSource = (DataSource) envContext.lookup("jdbc/fooddb");
		//Connection con = dataSource.getConnection();
	}
	
	public static DataAccess getInstance() throws NamingException, SQLException {
		if(singleton == null)
			singleton = new DataAccess();
			
		return singleton;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}