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

public Connection connect() {
	// SQLite connection string
	String url = WellnessSiteDB.DBURL;
	String username = WellnessSiteDB.DBUSER;
	String password = WellnessSiteDB.DBPWD;
	Connection conn = null;
	try {
		conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return conn;
}
}
