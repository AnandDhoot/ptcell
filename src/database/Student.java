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
	public static List<String> getStudentDetails(String RollNumber)
	{
		List<String> studentDetails = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from student where rollnumber=?");
			pstmt.setString(1, RollNumber);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next())
			{
				for(int i=1; i<=columnCount; i++)
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
}
