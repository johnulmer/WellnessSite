package com.lmig.application.unused;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.lmig.application.WellnessSiteDB;
import com.lmig.application.entities.WellnessEvent;

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
	
	public ArrayList<HeartrateActivityRow> getEventRows(int eventID) {
		ArrayList<HeartrateActivityRow> returnList = new ArrayList<HeartrateActivityRow>();
		String sql = "Select * from public.\"HEARTRATE\" WHERE \"EVENT_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, eventID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnList.add(new HeartrateActivityRow(
						rs.getInt("EVENT_ID"), 
						rs.getInt("MEMBER_ID"),
						rs.getTimestamp("ACTIVITY_TIME"),
						rs.getInt("HEARTRATE")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}
	
	
	public ArrayList<Integer> getEventList() {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "Select distinct \"EVENT_ID\" from public.\"HEARTRATE\"";
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
		String sql = "select distinct \"EVENT_ID\" from public.\"HEARTRATE\" where \"MEMBER_ID\" = ?";
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
		String dropTableSQL = "DROP TABLE IF EXISTS public.\"HEARTRATE\"";
		String createTableSQL = "CREATE TABLE public.\"HEARTRATE\"\r\n" + 
				"(\r\n" + 
				"    \"EVENT_ID\" integer,\r\n" + 
				"    \"MEMBER_ID\" integer,\r\n" + 
				"    \"HEARTRATE\" integer,\r\n" + 
				"    \"ACTIVITY_TIME\" timestamp with time zone\r\n" + 
				")\r\n" + 
				"WITH (\r\n" + 
				"    OIDS = FALSE\r\n" + 
				")\r\n" + 
				"TABLESPACE pg_default";
		resetRows();
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

		String updateRowsSQL = "INSERT INTO public.\"HEARTRATE\" (\"EVENT_ID\", \"MEMBER_ID\", \"ACTIVITY_TIME\", \"HEARTRATE\")\r\n" + 
				"VALUES\r\n" + 
				"(2, 1, '2017-06-20 11:01:00', 65),\r\n" + 
				"(2, 2, '2017-06-20 11:01:00', 72),\r\n" + 
				"(2, 3, '2017-06-20 11:01:00', 65),\r\n" + 
				"(2, 1, '2017-06-20 11:02:00', 80),\r\n" + 
				"(2, 2, '2017-06-20 11:02:00', 94),\r\n" + 
				"(2, 3, '2017-06-20 11:02:00', 75),\r\n" + 
				"(7, 1, '2017-07-20 11:01:00', 55),\r\n" + 
				"(7, 5, '2017-07-20 11:01:00', 64),\r\n" + 
				"(7, 8, '2017-07-20 11:01:00', 52),\r\n" + 
				"(7, 1, '2017-07-20 11:02:00', 63),\r\n" + 
				"(7, 5, '2017-07-20 11:02:00', 84),\r\n" + 
				"(7, 8, '2017-07-20 11:02:00', 74)";
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
