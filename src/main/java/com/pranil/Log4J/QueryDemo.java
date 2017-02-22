package com.pranil.Log4J;

import java.sql.*;
import java.util.Scanner;

public class QueryDemo {

	public static void main(String[] args) {
		
		QueryImp db_imp=new QueryImp();
		Statement stat=null;
		
		System.out.println("\n Enter the Database name:");
		Scanner dbScan=new Scanner(System.in);
		String database=dbScan.next();
		
		System.out.println("\n Enter the DB User name:");
		Scanner urScan=new Scanner(System.in);
		String username=urScan.next();
		
		System.out.println("\n Enter the DB pass word:");
		Scanner pwScan=new Scanner(System.in);
		String password=pwScan.next();
		
		try {
			stat=db_imp.connection(database, username, password);
			System.out.println("\n Database is succesfully connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		while(true)
		{
			System.out.println("\n Please select the required number ");
			System.out.println("Press 1 To Retrive");
			System.out.println("Press 2 To Update");
			System.out.println("Press 3 To Delete");
			System.out.println("Press 4 to Insert");
			System.out.println("Press 5 to exit");
			Scanner scan=new Scanner(System.in);
			int num=scan.nextInt();
			
			switch(num)
			{
			case 1: 
				db_imp.retrive(stat);
				break;
			case 2:
				db_imp.update(stat);
				break;
			case 3:
				db_imp.delete(stat);
				break;
			case 4:
				db_imp.insert(stat);
				break;

			case 5:
				dbScan.close();
				urScan.close();
				pwScan.close();
				scan.close();
				try {
					db_imp.cleanUp();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Successfully logout");
				System.exit(0);
			}
		}
	}
}

