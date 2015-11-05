package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Coordi
{
	public static List<String> getStudents(String id)
	{
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select rollnumber,name,department,approved "
							+ "from student "
							+ "where coordinatorid =?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				signedJAFs.add(rs.getString(1));
				signedJAFs.add(rs.getString(2));
				signedJAFs.add(rs.getString(3));
				signedJAFs.add(rs.getString(4));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting coordie's students");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
	public static List<String> getcJAFs(String id)
	{
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select name, jafnumber, stage, company.name ,companyid "
							+ "from jaf natural join company "
							+ "where companyid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				signedJAFs.add(rs.getString(1));
				signedJAFs.add(rs.getString(2));
				signedJAFs.add(rs.getString(3));
				signedJAFs.add(rs.getString(4));
				signedJAFs.add(rs.getString(5));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting company's JAF's");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
	public static List<String> getJAFs(String id)
	{
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select name, jafnumber, stage, company.name ,companyid "
							+ "from jaf natural join company "
							+ "where coordinatorid=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				signedJAFs.add(rs.getString(1));
				signedJAFs.add(rs.getString(2));
				signedJAFs.add(rs.getString(3));
				signedJAFs.add(rs.getString(4));
				signedJAFs.add(rs.getString(5));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting coordie's JAF's");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
}

