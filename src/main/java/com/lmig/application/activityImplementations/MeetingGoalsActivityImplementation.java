package com.lmig.application.activityImplementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.lmig.application.WellnessSiteDB;
import com.lmig.application.activityEntities.MeetingGoalsActivityRow;

public class MeetingGoalsActivityImplementation {
	
	public void insertRow(MeetingGoalsActivityRow meetingGoalsActivityRow) {
	
		String sql = "INSERT INTO public.'MEETING_GOALS' ('EVENT_ID', 'MEMBER_ID', 'ACTIVITY_DATE', 'ACTUAL', 'GOAL') VALUES (?, ?, ?, ?, ?)";
	
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, meetingGoalsActivityRow.getEventID());
			pstmt.setInt(2, meetingGoalsActivityRow.getMemberID());
			Date sqlDate = new Date(meetingGoalsActivityRow.getGoalDate().getTime());
			pstmt.setDate(3, sqlDate);
			pstmt.setDouble(4, meetingGoalsActivityRow.getActual());
			pstmt.setDouble(5, meetingGoalsActivityRow.getGoal());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<MeetingGoalsActivityRow> getEventRows(int eventID) {
		ArrayList<MeetingGoalsActivityRow> returnList = new ArrayList<MeetingGoalsActivityRow>();
		String sql = "Select * from public.\"MEETING_GOALS\" WHERE \"EVENT_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, eventID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(new MeetingGoalsActivityRow(
						rs.getInt("EVENT_ID"), 
						rs.getInt("MEMBER_ID"),
						rs.getDate("ACTIVITY_DATE"),
						rs.getDouble("ACTUAL"),
						rs.getDouble("GOAL")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}
	
	public ArrayList<Integer> getEventList() {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "Select distinct \"EVENT_ID\" from public.\"MEETING_GOALS\"";
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
		String sql = "select distinct \"EVENT_ID\" from public.\"MEETING_GOALS\" where \"MEMBER_ID\" = ?";
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
		String dropTableSQL = "DROP TABLE IF EXISTS public.\"MEETING_GOALS\"";
		String createTableSQL = "CREATE TABLE public.\"MEETING_GOALS\"\r\n" + 
				"(\r\n" + 
				"    \"EVENT_ID\" integer,\r\n" + 
				"    \"MEMBER_ID\" integer,\r\n" + 
				"    \"ACTIVITY_DATE\" date,\r\n" + 
				"    \"GOAL\" double precision,\r\n" + 
				"    \"ACTUAL\" double precision\r\n" + 
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
		String updateRowsSQL = "INSERT INTO public.\"MEETING_GOALS\" (\"EVENT_ID\", \"MEMBER_ID\", \"ACTIVITY_DATE\", \"ACTUAL\", \"GOAL\")\r\n" + 
				"VALUES\r\n" + 
				"(1, 1, '2017-06-20', 70, 64),\r\n" + 
				"(1, 2, '2017-06-20', 130, 128),\r\n" + 
				"(1, 3, '2017-06-20', 93, 96),\r\n" + 
				"(1, 1, '2017-06-21', 64, 64),\r\n" + 
				"(1, 2, '2017-06-21', 128, 128),\r\n" + 
				"(1, 3, '2017-06-21', 99, 128),\r\n" + 
				"(8, 1, '2017-07-20', 112, 128),\r\n" + 
				"(8, 4, '2017-07-20', 132, 128),\r\n" + 
				"(8, 7, '2017-07-20', 100, 128),\r\n" + 
				"(8, 1, '2017-07-21', 94, 128),\r\n" + 
				"(8, 4, '2017-07-21', 128, 128),\r\n" + 
				"(8, 7, '2017-07-21', 111, 128)";
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
