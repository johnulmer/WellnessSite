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
		String sql = "Select * from public.\"MEETING_GOALS\" WHERE \"EVENT_ID\" = ?";
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
