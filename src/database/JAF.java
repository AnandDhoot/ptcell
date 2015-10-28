package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JAF
{
	public static List<String> getSignedJAFs(String RollNumber)
	{
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select name, jafnumber, status "
					+ "from application natural join company "
					+ "where rollnumber=?");
			pstmt.setString(1, RollNumber);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				signedJAFs.add(rs.getString(1));
				signedJAFs.add(rs.getString(2));
				signedJAFs.add(rs.getString(3));
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
		int dept=0;
		float cpi=0;
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			// TODO - Also check endTime
			
			PreparedStatement pstmt = connection.prepareStatement(
					"select department,cpi from student where rollnumber=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			dept=DbUtils.encodeDepartments(rs.getString(1));
			cpi=rs.getFloat(2);
			}
			// TODO - Also check endTime
		pstmt = connection.prepareStatement(
					"select name, category, jafnumber, endTime, profile "
					+ "from jaf natural join company "
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
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from jaf where companyID=? and jafnumber=?");
			pstmt.setString(1, companyID);
			pstmt.setInt(1, jafNumber);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next())
			{
				for(int i=1; i<=columnCount; i++)
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
