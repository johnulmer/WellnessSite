package com.lmig.application.activityImplementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmig.application.WellnessSiteDB;
import com.lmig.application.activityEntities.PerformanceImprovementActivityRow;

public class PerformanceImprovementActivityImplementation {

	public ArrayList<PerformanceImprovementActivityRow> getEventRows(int eventID) {
		ArrayList<PerformanceImprovementActivityRow> returnList = new ArrayList<PerformanceImprovementActivityRow>();
		String sql = "Select * from public.\"PERFORMANCE_IMPROVEMENT\" WHERE \"EVENT_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, eventID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(new PerformanceImprovementActivityRow(
						rs.getInt("EVENT_ID"), 
						rs.getInt("MEMBER_ID"),
						rs.getDouble("START_STAT"),
						rs.getDouble("END_STAT")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}

	public ArrayList<Integer> getEventList() {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "Select distinct \"EVENT_ID\" from public.\"PERFORMANCE_IMPROVEMENT\"";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(rs.getInt("EVENT_ID"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}
	
	public ArrayList<Integer> getMemberEventList(int memberID) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "select distinct \"EVENT_ID\" from public.\"PERFORMANCE_IMPROVEMENT\" where \"MEMBER_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(rs.getInt("EVENT_ID"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}	
	
	public void reset() {
		String dropTableSQL = "DROP TABLE IF EXISTS public.\"PERFORMANCE_IMPROVEMENT\"";
		String createTableSQL = "CREATE TABLE public.\"PERFORMANCE_IMPROVEMENT\"\r\n" + 
				"(\r\n" + 
				"    \"EVENT_ID\" integer NOT NULL,\r\n" + 
				"    \"MEMBER_ID\" integer NOT NULL,\r\n" + 
				"    \"START_STAT\" double precision NOT NULL,\r\n" + 
				"    \"END_STAT\" double precision NOT NULL\r\n" + 
				")\r\n" + 
				"WITH (\r\n" + 
				"    OIDS = FALSE\r\n" + 
				")\r\n" + 
				"TABLESPACE pg_default";

		try (Connection conn = this.connect(); 
				PreparedStatement pstmtDrop = conn.prepareStatement(dropTableSQL);
				PreparedStatement pstmtCreate = conn.prepareStatement(createTableSQL);) {
			pstmtDrop.execute();
			pstmtCreate.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		resetRows();
	}
	
	private void resetRows() {
		String updateRowsSQL = "INSERT INTO public.\"PERFORMANCE_IMPROVEMENT\" \r\n" + 
				"(\"EVENT_ID\", \"MEMBER_ID\", \"START_STAT\", \"END_STAT\")\r\n" + 
				"VALUES\r\n" + 
				"(3, 2, 7.5, 5.0),\r\n" + 
				"(3, 5, 8.5, 4.25),\r\n" + 
				"(3, 7, 4.2, 3.1),\r\n" + 
				"(6, 2, 6.2, 5.4),\r\n" + 
				"(6, 3, 7.5, 6.25),\r\n" + 
				"(6, 4, 8.2, 7.1)";
		try (Connection conn = this.connect(); 
				PreparedStatement pstmtUpdateRows = conn.prepareStatement(updateRowsSQL);) {
			pstmtUpdateRows.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
public Connection connect() {
	// SQLite connection string
	WellnessSiteDB wsdb = new WellnessSiteDB();
	String url = wsdb.getDburl();
	String username = wsdb.getDbuser();
	String password = wsdb.getDbpwd();
	Connection conn = null;
	try {
		conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return conn;
}
	
}
