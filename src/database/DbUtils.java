package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils
{
	static String dbIP = "localhost";
	static String dbName = "testdb";
	static String dbUser = "root";
	static String dbPass = "root";
	static String[] depts = {"AE", "CS", "EE", "ME", "PH"};
	static Connection getConnection()
	{
		String dbURL = "jdbc:postgresql://" + dbIP + "/" + dbName;
		Connection connection = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("JDBC Driver not found");
		}
		catch (SQLException sqle)
		{
			System.out.println("Error in getting connection from the database");
		}
		return connection;
	}
	static void closeConnection(Connection connection)
	{
		try
		{
			connection.close();
		}
		catch (SQLException sqle)
		{
			System.out.println("Error in close database connetcion");
		}
	}
	
	// TODO - Improve this to binary search.
	static int getPosition(String dept)
	{
		for(int i=0; i<depts.length; i++)
			if(dept.equals(depts[i]))
				return i;
		return 0;
	}

	static Integer encodeDepartments(List<String> deptList)
	{
		int num = 0;
		for(String dept : deptList)
		{
			int pos = getPosition(dept);
			if(pos != -1)
				num += Math.pow(2, pos);
		}
		return num;
	}
	static List<String> decodeDepartments(int num)
	{
		List<String> deptList = new ArrayList<String>();
		int i = 1;
		while(num > 0)
		{
			if(num % 2 == 1)
				deptList.add(depts[depts.length - i]);
			num = num / 2;
			i++;
		}
		return deptList;
	}
}
