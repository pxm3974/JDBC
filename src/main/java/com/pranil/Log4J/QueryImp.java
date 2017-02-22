package com.pranil.Log4J;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QueryImp {
	
	final static String driverName="com.mysql.jdbc.Driver";

	private Statement statement;
	private Connection con;
	private ResultSet resultset;
	private ResultSetMetaData metaData;
	private int numOfRows;
	private int numOfColumns;
	private String queryString;
	private Scanner queryScan;
	
	public Statement connection(String database, String username, String password) throws ClassNotFoundException
	{
		String database_url="jdbc:mysql://localhost/" + database;
		
		Class.forName(driverName);	
		try {
			con=DriverManager.getConnection(database_url, username, password);
			statement=con.createStatement();
			DatabaseMetaData md = con.getMetaData();
		    ResultSet rs = md.getTables(null, null, "%", null);
		    System.out.println("\t List of tables in selected databases");
		    while (rs.next()) {
		        System.out.println(rs.getString(3));
		      }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return statement;
	}
	
	public void retrive(Statement stat)
	{
		try {
			System.out.println("Please enter the select query: ");
			System.out.println("Ex: select 'columnName' from 'tableName' ");
			queryScan=new Scanner(System.in);
			queryString=queryScan.nextLine();
			
			resultset=stat.executeQuery(queryString);
			metaData=resultset.getMetaData();
			resultset.last();
			numOfRows=resultset.getRow();
			display();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Statement stat)
	{
		try {
			System.out.println("Please enter the select query: ");
			System.out.println("Ex: insert into 'tablename'(if applicable column1, column2) values (value1, value2 ");
			queryScan=new Scanner(System.in);
			queryString=queryScan.nextLine();
			stat.executeUpdate(queryString);
			System.out.println("Data has been inserted!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Statement stat)
	{
		try {
			System.out.println("Please enter the select query: ");
			System.out.println("update 'tablename' set columnName1=value1 where criteria");
			queryScan=new Scanner(System.in);
			queryString=queryScan.nextLine();
			stat.executeUpdate(queryString);
			System.out.println("Data has been updated!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Statement stat)
	{
		try {
			System.out.println("Please enter the select query: ");
			System.out.println("delete from tablename where criteria");
			queryScan=new Scanner(System.in);
			queryString=queryScan.nextLine();
			stat.executeUpdate(queryString);
			System.out.println("Data has been deleted!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void display() throws SQLException
	{
		ArrayList<String> columnName=new ArrayList<String>();
		ArrayList<ArrayList<Object>> columnValue=new ArrayList<ArrayList<Object>>();
		
		numOfColumns=metaData.getColumnCount();
		for (int i=1; i<=numOfColumns;i++)
		{
			columnName.add(metaData.getColumnName(i));
		}
		
		for(int j=1;j<=numOfRows;j++)
		{
			ArrayList<Object> col=new ArrayList<Object>();
			for(int n=1;n<=numOfColumns;n++)
			{
				resultset.absolute(j);
				col.add(resultset.getObject(n));
			}
			columnValue.add(col);
		}
		
		for (int i=0; i<numOfColumns;i++)
		{
			System.out.print(columnName.get(i)+ "\t");
		}
		
		System.out.println();
		
		for(int j=0;j<numOfRows;j++)
		{
			for(int n=0;n<numOfColumns;n++)
			{
				System.out.print(columnValue.get(j).get(n) +"\t");
			}
			System.out.println();
		}
	}
	
	public void cleanUp() throws SQLException
	{
		con.close();
	}

}

