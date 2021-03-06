package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JAF
{
	public static void addNewJAF(String companyID, java.util.Date endDateTime, String cpicutoff, String[] deptElig,
			String location, String salary, String description, String profile)
	{		
		java.sql.Date endDate = new java.sql.Date(endDateTime.getTime());
		java.sql.Timestamp t = new Timestamp(endDate.getTime());
		
		System.out.println("--- " + t + " ---");
		
		Float cpiCutoff = Float.parseFloat(cpicutoff);
		Integer sal = Integer.parseInt(salary);
		int deptEligible = DbUtils.encodeDepartments(deptElig);
		int oldMaxNum = 0;
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select max(jafnumber) from jaf where companyid=?");

			pstmt.setString(1, companyID);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				oldMaxNum = rs.getInt(1);
			}
			
			pstmt = connection.prepareStatement(
					"insert into jaf "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, 0, ?)");
			pstmt.setString(1, companyID);
			pstmt.setInt(2, oldMaxNum + 1);
			pstmt.setTimestamp(3, t);
			pstmt.setFloat(4, cpiCutoff);
			pstmt.setInt(5, deptEligible);
			pstmt.setString(6, location);
			pstmt.setInt(7, sal);
			pstmt.setString(8, description);
			pstmt.setString(9, profile);

			System.out.println(pstmt.toString());
			pstmt.executeUpdate();
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when making new JAF");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
	}

	public static void chgJAFStage(int status, int JAFno, String CompanyID)
	{
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("update jaf set stage=? " + "where companyid=? and jafnumber=?");
			pstmt.setInt(1, status);
			pstmt.setString(2, CompanyID);
			pstmt.setInt(3, JAFno);
			pstmt.executeUpdate();

		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting chaging  JAF stage");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}

	}

	public static List<String> getSignedJAFs(String RollNumber)
	{
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select name, jafnumber, status, companyID "
					+ "from application natural join company " + "where rollnumber=?");
			pstmt.setString(1, RollNumber);
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
			System.out.println("SQL exception when getting signed JAFs");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}

	public static List<String> getOpenEligibleJAFs(String id)
	{
		List<String> openJAFs = new ArrayList<String>();
		int dept = 0;
		float cpi = 0;
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select department,cpi from student where rollnumber=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				dept = DbUtils.encodeDepartments(rs.getString(1));
				cpi = rs.getFloat(2);
			}
			pstmt = connection.prepareStatement(
					"select name, category, jafnumber, endTime, profile, companyid " + "from jaf natural join company "
							+ "where (depteligible | ? > 0) and cpicutoff < ? and now() <= endTime");
			pstmt.setInt(1, dept);
			pstmt.setFloat(2, cpi);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				openJAFs.add(rs.getString(1));
				openJAFs.add(rs.getString(2));
				openJAFs.add(rs.getString(3));
				openJAFs.add(rs.getString(4));
				openJAFs.add(rs.getString(5));
				openJAFs.add(rs.getString(6));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting openJAFs");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return openJAFs;
	}

	public static List<String> getJAFDetails(String companyID, int jafNumber)
	{
		List<String> JAFDetails = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select * from jaf where companyID=? and jafnumber=?");
			pstmt.setString(1, companyID);
			pstmt.setInt(2, jafNumber);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next())
			{
				for (int i = 1; i <= columnCount; i++)
				{
					JAFDetails.add(rsmd.getColumnName(i));
					JAFDetails.add(rs.getString(i));
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting JAF details");
			System.out.println(sqle.getMessage());
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return JAFDetails;
	}
}
