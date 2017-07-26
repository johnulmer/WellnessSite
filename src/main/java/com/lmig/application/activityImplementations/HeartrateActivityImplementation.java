package com.lmig.application.activityImplementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lmig.application.WellnessSiteDB;
import com.lmig.application.activityEntities.HeartrateActivityRow;

public class HeartrateActivityImplementation {

	public void insertRow(HeartrateActivityRow heartrateActivityRow) {

		String sql = "INSERT INTO public.'HEARTRATE' "
				+ "('EVENT_ID', 'MEMBER_ID', 'ACTIVITY_TIME', 'HEARTRATE') "
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, heartrateActivityRow.getEventID());
			pstmt.setInt(2, heartrateActivityRow.getMemberID());
			Timestamp ts = new Timestamp(heartrateActivityRow.getStatTimestamp().getTime());
			pstmt.setTimestamp(3, ts);
			pstmt.setInt(4, heartrateActivityRow.getStat());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection connect() {
		// SQLite connection string
		String url = WellnessSiteDB.DBURL;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
