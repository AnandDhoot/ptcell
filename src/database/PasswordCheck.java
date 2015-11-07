package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordCheck
{
	public static List<String> getStudentPassword(String id)
	{
		List<String> str = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select password, approved, name from student where rollnumber=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				str.add(rs.getString(1));
				str.add(rs.getString(2));
				str.add(rs.getString(3));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting student password");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return str;
	}
	public static List<String> getCoordinatorPassword(String id)
	{
		List<String> str = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select password, name from coordinator where coordinatorid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				str.add(rs.getString(1));
				str.add(rs.getString(2));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting coordinator password");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}

		return str;
	}
	public static List<String> getCompanyPassword(String id)
	{
		List<String> str = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select password, name from company where companyid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				str.add(rs.getString(1));
				str.add(rs.getString(2));
			}

		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting company password");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}

		return str;
	}
}
