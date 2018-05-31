package com.ubaid.Model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//I am going to make an inheritance 
//this class is super class
//this class connect to Database
//this class has
//a static method 
//getconnection and this method is assigned to a variavle connection
//basic purpose of this method to established a connection
public class DataBase
{
	
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pa_db?useSSL=false";
	public static Connection connection = getConnection();
	public  Statement statement;
	public  ResultSet resultSet;
	protected ResultSetMetaData metaData;
	static int counter = 0;
	
	public DataBase()
	{
	}
	
	
	//the following method is static method for all 
	//which return connection 
	public static Connection getConnection()
	{
		if(counter == 1)
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, "name", "password");
			System.out.println("OK: Connection Secured");
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return connection;
	}
		
}
