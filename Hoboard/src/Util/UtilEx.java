package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Reserve.Reserve_Dto;
import db.DBClose;
import db.DBConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class UtilEx {
	public static void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);
	}
	
	public static int getAllCountTable(String tableName, String choice, String searchWord) {
		String sql = " SELECT COUNT(*) FROM "+tableName.toUpperCase()+" ";
		String sqlWord = " WHERE DEL = 0 ";
		if((choice != null || "".equals(choice)) && (searchWord != null || "".equals(searchWord))) {
			switch(tableName) {
				case "REVIEW":
					if (choice.equals("id"))			sqlWord += " AND INDVD_ID='" + searchWord.trim() + "'";
					else if (choice.equals("busi_name"))sqlWord += " AND BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("title"))	sqlWord += " AND TITLE LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("content"))	sqlWord += " AND CONTENT LIKE '%" + searchWord.trim() + "%' ";
					else if (choice.equals("score"))	sqlWord += " AND SCORE='" + searchWord.trim() + "'";
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
		System.out.println(sql);
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
	// 어디서쓰니?....
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
		else
			return false;
	}
	
	// date to timestamp
	public static String dateToTimestamp(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date d = (Date) sdf.parse(date);
		long real = d.getTime();
		return real+"";
	}
	// 혹시 몰라서 넣어둠 ㅎ
	public static JSONArray listToJson (List<LinkedHashMap<Reserve_Dto,String>> list) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(mapToJson(list.get(1)));
		System.out.println(jsonArray.size());
		return jsonArray;
	}
	// MyReserverController에서 사용  << map to json
	public static JSONObject mapToJson (LinkedHashMap<Reserve_Dto, String> map) {
		JSONObject json = new JSONObject();
		for (Entry<Reserve_Dto, String> entry : map.entrySet()) {
			Reserve_Dto key = entry.getKey();
			Object value = entry.getValue();
			json.put(value+key.getReserve_date(), key);
		}
		return json;
	}

	
}
