package com.lmig.application.activityImplementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmig.application.WellnessSiteDB;
import com.lmig.application.activityEntities.StepsOverTimeActivityRow;

public class StepsOverTimeActivityImplementation {
	
	public ArrayList<StepsOverTimeActivityRow> getEventRows(int eventID) {
		ArrayList<StepsOverTimeActivityRow> returnList = new ArrayList<StepsOverTimeActivityRow>();
		String sql = "Select * from public.\"STEPS_OVER_TIME\" WHERE \"EVENT_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, eventID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(new StepsOverTimeActivityRow(
						rs.getInt("EVENT_ID"), 
						rs.getInt("MEMBER_ID"),
						rs.getDate("ACTIVITY_DATE"),
						rs.getInt("STEP_COUNT")));
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
