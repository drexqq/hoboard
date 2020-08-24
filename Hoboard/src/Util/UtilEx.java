package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBClose;
import db.DBConnection;

public class UtilEx {
	public static void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);
	}
	
	public static int getAllCountTable(String tableName, String choice, String searchWord) {
		String sql = " SELECT COUNT(*) FROM "+tableName.toUpperCase()+" ";
		String sqlWord = "";
		if((choice != null || "".equals(choice)) && (searchWord != null || "".equals(searchWord))) {
			switch(tableName) {
				case "REVIEW":
					if (choice.equals("id"))			sqlWord = " WHERE INDVD_ID='" + searchWord.trim() + "'";
					else if (choice.equals("busi_name"))sqlWord = " WHERE BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("title"))	sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("content"))	sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("score"))	sqlWord = " WHERE SCORE='" + searchWord.trim() + "'";
					break;
				case "NEWS":
					System.out.println("news");
					break;
				case "RESERVE":
					System.out.println("reserve");
					break;
			}
		}
		sql += sqlWord;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int len = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) len = rs.getInt(1);
		} 
		catch (Exception e) { e.printStackTrace(); }
		finally { DBClose.close(psmt, conn, rs); }
		return len;
	}
	
	public static int getUsersCountTable (String tableName, String choice, String searchWord, String id) {
		String sql = " SELECT COUNT(*) FROM "+tableName.toUpperCase()+" ";
		String sqlWord = "";
		if((choice != null || "".equals(choice)) && (searchWord != null || "".equals(searchWord))) {
			switch(tableName) {
				case "REVIEW":
					if (choice.equals("id"))			sqlWord = " WHERE INDVD_ID='" + searchWord.trim() + "'";
					else if (choice.equals("busi_name"))sqlWord = " WHERE BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("title"))	sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("content"))	sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("score"))	sqlWord = " WHERE SCORE='" + searchWord.trim() + "'";
					break;
				case "NEWS":
					System.out.println("news");
					break;
				case "RESERVE":
					System.out.println("reserve");
					break;
			}
		}
		sql += sqlWord;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int len = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) len = rs.getInt(1);
		} 
		catch (Exception e) { e.printStackTrace(); }
		finally { DBClose.close(psmt, conn, rs); }
		return len;
	}
	
	// number regExp
	public static boolean numCheck(String str) {
		String regExp = "^[0-9]+$";
		if (str.matches(regExp)) return true;
		else return false;
	}
}
