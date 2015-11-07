package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class applies
{
	public static List<String> getApplied(String cid,String jafNum){
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select rollnumber,jafnumber, status "
							+ "from application "
							+ "where companyid =? and jafnumber=?");
			pstmt.setString(1, cid);
			pstmt.setInt(2, Integer.parseInt(jafNum));
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
			System.out.println("SQL exception when getting company's students");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
	
	public static void updateStage(String studentID, Integer level){
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"update application set  status = ? where name = ?");
			pstmt.setString(1, studentID);
			pstmt.setInt(2, level);
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when updating student level");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
	}
	
	public static List<String> getLevel(String jafNum, Integer level){
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select rollnumber, status "
							+ "from application "
							+ "where jafnumber=? and status=?");
			pstmt.setString(1,jafNum);
			pstmt.setInt(2, level);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				signedJAFs.add(rs.getString(1));
				signedJAFs.add(rs.getString(2));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("SQL exception when getting company's students");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
	
	public static List<String> getStudents(){
		List<String> signedJAFs = new ArrayList<String>();
		Connection connection = null;
		try
		{
			connection = DbUtils.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select rollnumber,jafnumber, status "
							+ "from application "
							+ "where status >=0");
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
			System.out.println("SQL exception when getting company's students");
		}
		finally
		{
			DbUtils.closeConnection(connection);
		}
		return signedJAFs;
	}
	
	
	
	
	
	
	
	
	
}
