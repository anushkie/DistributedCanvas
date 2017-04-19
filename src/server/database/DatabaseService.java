package server.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.*;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author anushka
 *
 */
public class DatabaseService {
	
	private Connection connection;

	public DatabaseService() {
		
		try {
			this.connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canvasentries", "root", "mysql");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void registerUser(String firstName, String lastName, String userName, String password, String emailId, String gender) {
		
		try {
			
			Statement statement = connection.createStatement();
			
			String string = "insert into canvas values (" + "'" +firstName + "'" + "," +  "'" + lastName + "'" + "," + "'" + userName + "'" + "," + "'" + password + "'" + "," +  "'" + emailId + "'" + "," + "'" + gender + "'" + ")";
			int result = statement.executeUpdate(string);
		}
		catch(Exception exp) {
			exp.printStackTrace();
		}
	}
	

	public boolean isValidUser(String username, String password) {
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery("select count(*) from canvas where username =" + "'" + username + "'" + " AND password = " +  "'" + password + "'" );
			resultSet2.next();
			int count = resultSet2.getInt(1);
			
			if(count == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doesUserNameExists(String userName) {
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery("select count(*) from canvas where username =" + "'" + userName + "'" );
			resultSet2.next();
			int count = resultSet2.getInt(1);
			if(count == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}

		return false;
	}
	
	public boolean doesEmailExists(String emailId) {

		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery("select count(*) from canvas where emailId =" + "'" + emailId + "'" );
			resultSet2.next();
			int count = resultSet2.getInt(1);			
			if(count == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		  }

		return false;
	}
}
