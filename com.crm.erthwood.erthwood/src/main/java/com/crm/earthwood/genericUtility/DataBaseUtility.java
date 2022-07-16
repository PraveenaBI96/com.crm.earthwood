package com.crm.earthwood.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
/**
 * It's used to perform any actions in Database
 * @author Praveen
 */
	static Driver driverRef;
	static Connection conn;
	static ResultSet result;
	/**
	 * It's used to connect to DataBase
	 * @throws SQLException 
	 * 
	 */
		public void connectDB() throws SQLException
		{
			try {
	      driverRef=new Driver();
	      DriverManager.registerDriver(driverRef);
	      conn=DriverManager.getConnection("jdbc:mysql://localhost3306/students","root","root");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * It's used to close the DataBase Connection
		 */
		public void closeDB()
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 * This method is used to execute Query
		 * @throws SQLException 
		 * 
		 */
		public ResultSet executeQuery(String query) throws SQLException
		{
			result = conn.createStatement().executeQuery(query);
			return result;
		}
		/**
		 * This method is used to execute Query
		 * @throws SQLException 
		 * @param query
		 * @param result
		 */
		public int executeUpdate(String query) throws SQLException
		{
			int result = conn.createStatement().executeUpdate(query);
			return result;
		}
		/**
		 * This method will execute the Query and Verify
		 * @throws SQLException 
		 */
		public void executeQueryAndVerify(String query, int columnNum,String expectedData) throws SQLException
		{
			result=conn.createStatement().executeQuery(query);
			while(result.next())
			{
				if(result.getString(columnNum).equals(expectedData))
				{
					System.out.println("Data is verified in the Database");
				}else
				{
					System.out.println("Data is not Verified in the DataBase");
				}
			}
		}
}