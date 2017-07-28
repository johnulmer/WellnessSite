package com.lmig.application.unused;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lmig.application.WellnessSiteDB;

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
	
	public ArrayList<Integer> getEventList() {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "Select distinct \"EVENT_ID\" from public.\"STEPS_OVER_TIME\"";
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
	
	public IntSummaryStatistics stepsOverTimeStatSummary(int eventID) {
		//ArrayList<Integer> returnList = new ArrayList<Integer>();
		IntSummaryStatistics stepSummary = new IntSummaryStatistics();
		String sql = "select \"STEP_COUNT\" from public.\"STEPS_OVER_TIME\" where \"EVENT_ID\" = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, eventID);
			ResultSet rs = pstmt.executeQuery();
			Array rsArray = rs.getArray("STEP_COUNT");
			Integer[] stepArray = (Integer[])rsArray.getArray();
		    stepSummary = Arrays.stream(stepArray)
		    		.collect(
		    				Collectors.summarizingInt((Integer x) -> x));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return stepSummary;
	}
	
	public ArrayList<Integer> getMemberEventList(int memberID) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		String sql = "select distinct \"EVENT_ID\" from public.\"STEPS_OVER_TIME\" where \"MEMBER_ID\" = ?";
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
		String dropTableSQL = "DROP TABLE IF EXISTS public.\"STEPS_OVER_TIME\"";
		String createTableSQL = "CREATE TABLE public.\"STEPS_OVER_TIME\"\r\n" + 
				"(\r\n" + 
				"    \"EVENT_ID\" integer NOT NULL,\r\n" + 
				"    \"MEMBER_ID\" integer NOT NULL,\r\n" + 
				"    \"ACTIVITY_DATE\" date NOT NULL,\r\n" + 
				"    \"STEP_COUNT\" integer NOT NULL\r\n" + 
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
		String updateRowsSQL = "INSERT INTO public.\"STEPS_OVER_TIME\" \r\n" + 
				"(\"EVENT_ID\", \"MEMBER_ID\", \"ACTIVITY_DATE\", \"STEP_COUNT\")\r\n" + 
				"VALUES\r\n" + 
				"(4, 1, '2017-06-20', 3750),\r\n" + 
				"(4, 2, '2017-06-20', 2000),\r\n" + 
				"(4, 3, '2017-06-20', 1750),\r\n" + 
				"(4, 1, '2017-06-21', 2000),\r\n" + 
				"(4, 2, '2017-06-21', 3000),\r\n" + 
				"(4, 3, '2017-06-21', 2500),\r\n" + 
				"(4, 1, '2017-06-22', 8000),\r\n" + 
				"(4, 2, '2017-06-22', 6000),\r\n" + 
				"(4, 3, '2017-06-22', 2500),\r\n" + 
				"(4, 1, '2017-06-23', 4000),\r\n" + 
				"(4, 2, '2017-06-23', 5500),\r\n" + 
				"(4, 3, '2017-06-23', 2275),\r\n" + 
				"(4, 1, '2017-06-24', 1500),\r\n" + 
				"(4, 2, '2017-06-24', 1750),\r\n" + 
				"(4, 3, '2017-06-24', 2250),\r\n" + 
				"(4, 1, '2017-06-25', 2156),\r\n" + 
				"(4, 2, '2017-06-25', 4438),\r\n" + 
				"(4, 3, '2017-06-25', 1145),\r\n" + 
				"(4, 1, '2017-06-26', 2354),\r\n" + 
				"(4, 2, '2017-06-26', 6500),\r\n" + 
				"(4, 3, '2017-06-26', 3400),\r\n" + 
				"(4, 1, '2017-06-27', 2250),\r\n" + 
				"(4, 2, '2017-06-27', 2345),\r\n" + 
				"(4, 3, '2017-06-27', 17500),\r\n" + 
				"(5, 5, '2017-07-20', 5000),\r\n" + 
				"(5, 2, '2017-07-20', 7500),\r\n" + 
				"(5, 3, '2017-07-20', 3000),\r\n" + 
				"(5, 5, '2017-07-21', 8000),\r\n" + 
				"(5, 2, '2017-07-21', 9500),\r\n" + 
				"(5, 3, '2017-07-21', 6500),\r\n" + 
				"(5, 5, '2017-07-22', 11000),\r\n" + 
				"(5, 2, '2017-07-22', 10000),\r\n" + 
				"(5, 3, '2017-07-22', 9500),\r\n" + 
				"(5, 5, '2017-07-23', 5000),\r\n" + 
				"(5, 2, '2017-07-23', 8000),\r\n" + 
				"(5, 3, '2017-07-23', 3500),\r\n" + 
				"(5, 5, '2017-07-24', 5500),\r\n" + 
				"(5, 2, '2017-07-24', 7750),\r\n" + 
				"(5, 3, '2017-07-24', 3750),\r\n" + 
				"(5, 5, '2017-07-25', 1500),\r\n" + 
				"(5, 2, '2017-07-25', 1750),\r\n" + 
				"(5, 3, '2017-07-25', 900),\r\n" + 
				"(5, 5, '2017-07-26', 4500),\r\n" + 
				"(5, 2, '2017-07-26', 8700),\r\n" + 
				"(5, 3, '2017-07-26', 2300),\r\n" + 
				"(5, 5, '2017-07-27', 5500),\r\n" + 
				"(5, 2, '2017-07-27', 9750),\r\n" + 
				"(5, 3, '2017-07-27', 4000)";
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
