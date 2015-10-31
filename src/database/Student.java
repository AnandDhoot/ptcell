package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student
{
	// TODO - Add resume to the table
	public static void chgStatus(int status,String rollno){
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"update student set approved=? "
							+ "where rollnumber=?");
			pstmt.setInt(1, status);
			pstmt.setString(2, rollno);
			pstmt.executeUpdate();
		
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting chaging  Studetn Status");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
	
	
}
	public static List<String> getStudentDetails(String RollNumber)
	{
		List<String> studentDetails = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from student where rollnumber=?");
			pstmt.setString(1, RollNumber);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next())
			{
				for (int i = 1; i <= columnCount; i++)
				{
					studentDetails.add(rsmd.getColumnName(i));
					studentDetails.add(rs.getString(i));
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting student details");
			System.out.println(sqle.getMessage());
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return studentDetails;
	}

	public static void updateStudentDetails(String rollnumber, String email, String mob, String addr)
	{
		int mobile = Integer.parseInt(mob);
		Connection connection = null;
		System.out.println(rollnumber + " " + email + " " + mob + " " + addr);
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("update student "
					+ "set emailid=?, mobilenumber=?, address=? " + "where rollnumber=?");
			pstmt.setString(1, email);
			pstmt.setInt(2, mobile);
			pstmt.setString(3, addr);
			pstmt.setString(4, rollnumber);
			pstmt.executeUpdate();
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when updating student details");
			System.out.println(sqle.getMessage());
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
	}
}
