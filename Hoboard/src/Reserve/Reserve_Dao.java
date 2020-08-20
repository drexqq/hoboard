package Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import member.Member_Dto;

public class Reserve_Dao {

	private static Reserve_Dao dao = new Reserve_Dao();

	private Reserve_Dao() {

	}

	public static Reserve_Dao getInstance() {
		return dao;
	}
	
	
	// TODO search all write count
		public int getsearch_allcount(String loc, String amt, String word) {

			String sql = " SELECT COUNT(*) FROM MEMBER " 
					   + " WHERE ID IN ";

			String sqlamt = "";
			String sqlloc = "";
			String sqlword = "";
			
			if (loc == null && amt == null && word == null) {
			String sql2 = " SELECT COUNT(*) FROM MEMBER "
					+ " WHERE AUTH = 2 ";
				
				sql = sql2;
			} else if (loc != null && amt != null && word == null) {
				sqlamt = " (SELECT ID FROM BUSI_CATE WHERE " + amt + " = 1) ";
				sqlloc = " AND ADDRESS LIKE '%" + loc + "%' ";
				sql += sqlamt + sqlloc; 
			
			} else if (loc != null && amt != null && word != null) {
				sqlamt = " (SELECT ID FROM BUSI_CATE WHERE " + amt + " = 1) ";
				sqlloc = " AND ADDRESS LIKE '%" + loc + "%' ";
				sqlword = "AND NAME LIKE '%" + word + "%'";
				
				sql += sqlamt + sqlloc + sqlword;
			}
			
			
			System.out.println(sql);

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			int len = 0;

			try {
				conn = DBConnection.getConnection();

				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();
				if (rs.next()) {
					len = rs.getInt(1);
				}
				System.out.println(len);
			} catch (Exception e) {
				System.out.println("getsearch fail");
				e.printStackTrace();
			} finally {
				DBClose.close(psmt, conn, rs);
			}
			System.out.println("search done");
			return len;
		}
	
	// TODO search
	public List<Member_Dto> getSearch_list(String loc, String amt, String searchWord, int limit, int page) {
		String sql = " SELECT * FROM " 
				   + " (SELECT ROW_NUMBER()OVER(ORDER BY NAME ASC) AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM "
				   + " FROM MEMBER ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();

		String sqlamt = "";
		String sqlloc = "";
		String sqlword = "";
		
		if(loc.equals("") && amt.equals("") && searchWord.equals("")) {
			String sql1 = " SELECT * FROM( "
				+ " SELECT ROW_NUMBER()OVER(ORDER BY NAME ASC) AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM " 
				+ " FROM MEMBER "
				+ " WHERE AUTH = 2) ";
			sql = sql1;
		} else if(loc != null && amt != null && searchWord == null) {
			sqlamt = " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE " + amt + " = 1) ";
			sqlloc = " AND ADDRESS LIKE '%" + loc + "%' )";
			
			sql += sqlamt + sqlloc;
		} else if (loc != null && amt != null && searchWord != null) {
			
			sqlamt = " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE " + amt + " = 1) ";
			sqlloc = " AND ADDRESS LIKE '%" + loc + "%' ";
			sqlword = " AND NAME LIKE '%" + searchWord.trim() + "%') ";
			
			sql = sql + sqlamt + sqlloc + sqlword;
		}
		
		sql += " WHERE RNUM >= ? AND RNUM <= ? ";
		
		System.out.println(sql);
		
		
		int start, end;
		start = 1 + limit * page; // 시작 글의 번호
		end = limit + limit * page; // 끝 글의 번호
		
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearch_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/6 getSearch_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getSearch_list success");

			while (rs.next()) {

				int auth = rs.getInt("AUTH");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String tel = rs.getString("TEL");
				String email = rs.getString("EMAIL");
				String address = rs.getString("ADDRESS");
				String d_address = rs.getString("D_ADDRESS");
				String post_num = rs.getString("POST_NUM");

				list.add(new Member_Dto(auth, id, name, tel, email, address, d_address, post_num));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;

	}

	


	public List<Member_Dto> getCate_list(String cate, int num) {
		String sql = " SELECT AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM " + " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE " + cate + " = ? ) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();

		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getInternal_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);

			System.out.println("2/6 getInternal_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getInternal_list success");

			while (rs.next()) {

				int auth = rs.getInt("AUTH");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String tel = rs.getString("TEL");
				String email = rs.getString("EMAIL");
				String address = rs.getString("ADDRESS");
				String d_address = rs.getString("D_ADDRESS");
				String post_num = rs.getString("POST_NUM");

				list.add(new Member_Dto(auth, id, name, tel, email, address, d_address, post_num));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	//public List<Member_Dto> getDetail_list(String name) {
	//	String sql = " SELECT  "
		
		
		
		
	//}
}