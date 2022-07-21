package com.training.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class jdbc_demo {
	public static void main(String[] args) {
			
		Scanner scn=new Scanner(System.in);
		String name="A",city="S";
		int accno,amt=0;
		
		ArrayList<Account> list =new ArrayList<>();
		
		System.out.println("Greetings of the day, Select your choice :) ");
        System.out.println("1. Open New Account");
		System.out.println("2. Deposit Money");
		System.out.println("3. Withdraw Money");
		System.out.println("4. Account Statement");
		System.out.println("5. List All Users");
		System.out.println("6. Exit");

		int choice;
		boolean flag;
		do
		{
		System.out.print("Enter Choice here");
		 choice =scn.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("***Opening Account***");
			System.out.print("Enter Name");
			name =scn.next();
			System.out.print("Enter City");
			city=scn.next();
			System.out.println("Enter Amount");
			amt=scn.nextInt();
			Account acc=new Account(name,city,amt);
			list.add(acc);
			System.out.println("Account Opened Succesfuly");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:xe";
				String username="banker";
				String Password="12345";
						
				Connection con=DriverManager.getConnection(url,username,Password);
				
				Statement stmt=con.createStatement();
				
				String sql = "INSERT INTO Bank VALUES("+acc.getAccno()+",'"+acc.getName()+"','"+acc.getCity()+"',"+acc.getBal()+")";
				stmt.executeUpdate(sql);
	     
				
				
			}
			 catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("***Account Deposit***");
			flag =false;
			System.out.println("Enter Account number");
			accno=scn.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					flag =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
					System.out.println("\nEnter amount to deposit");
					amt=scn.nextInt();
					ac.setBal(ac.getBal()+amt);
					System.out.println("Deposit Successfully");
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String username="banker";
						String Password="12345";
								
						Connection con=DriverManager.getConnection(url,username,Password);
						
						Statement stmt=con.createStatement();
						
						String sql = "UPDATE bank SET amt = '"+ac.getBal()+"'";
						stmt.executeUpdate(sql);
			     
						
						
					}
					 catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					System.out.println("Customer Account Balance "+ac.getBal());
					break;
					
				}
			}
			if(!flag)
			{
				System.out.println("Invalid Account Number");
			}
			
			break;
		case 3:
			System.out.println("***Account withdraw***");
			flag =false;
			System.out.println("Enter Account number");
			accno=scn.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					flag =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
					System.out.println("\nEnter amount to withdraw");
					amt =scn.nextInt();
					if(amt<=ac.getBal())
					{
						ac.setBal(ac.getBal()-amt);
						System.out.println("withdraw Successfully");
						System.out.println("Customer Account Balance "+ac.getBal());
					}
					else {
						System.out.println("Insufficient Balance!");
					}
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String url="jdbc:oracle:thin:@localhost:1521:xe";
						String username="banker";
						String Password="12345";
								
						Connection con=DriverManager.getConnection(url,username,Password);
						
						Statement stmt=con.createStatement();
						
						String sql = "UPDATE bank SET amt = '"+ac.getBal()+"'";
						stmt.executeUpdate(sql);
			     
						
						
					}
					 catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				
					
					break;
				}
			}
			if(!flag)
			{
				System.out.println("Invalid Account Number");
			}
			
			break;
		case 4:
			System.out.println("***Account Balance enquiry***");
			flag =false;
			System.out.println("Enter Account number");
			accno=scn.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					flag =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
				}
			}
			if(!flag)
			{
				System.out.println("Invalid Account Number");
			}
			break;
		case 5:
			System.out.println("***List All Accounts***");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url ="jdbc:oracle:thin:@localhost:1521:xe";
				String username="banker";
				String password="12345";
				Connection con = DriverManager.getConnection(url, username, password);
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from Bank");
				while(rs.next()) {
					System.out.println("AccountNumber:" + rs.getString(1)+ " ename:" + rs.getString(2) + " City:" + rs.getString(3) + " Amount:" + rs.getString(4));
				}}
				
				
				
			 catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("***Thanks:)***");
			break;
			
		default:	
			System.out.println("***Invalid choice***");
			break;
		}
		
		}while(choice!=6);
		
	}
	
}