package Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBClose;
import db.DBConnection;
import member.BUSI_Cate_Dto;
import member.BUSI_Member_Dto;
import member.BUSI_Time_Dto;
import member.Member_Dto;
import review.Review_Dto;

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
	
	public int getScore_avg(String id) {
		String sql = " SELECT AVG(A.SCORE) "
				  +  " FROM REVIEW A INNER JOIN MEMBER B "
				  +  " ON  A.BUSI_ID = B.ID "
		          +  " WHERE B.ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int score = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getScore_avg success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getScore_avg success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getScore_avg success");
			
			if (rs.next()) {
				score = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return score;
	}
	
	public String getHomepage(String name){
		String sql = " SELECT HOMEPAGE "
				  +  " FROM BUSI_MEMBER A INNER JOIN MEMBER B "
				  +  " ON  A.ID = B.ID "
		          +  " WHERE B.ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String home = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHomepage success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			System.out.println("2/6 getHomepage success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getHomepage success");
			
			if (rs.next()) {
				home = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return home;
	
	}
	
	public List<Member_Dto> getMember_list(String id){
		String sql =  " SELECT NAME , TEL , ADDRESS "
				  +  " FROM MEMBER "
		          +  " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getMember_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getMember_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getMember_list success");
			
			if (rs.next()) {
				
		
				String name = rs.getString("NAME");
				String tel = rs.getString("TEL");
				String address = rs.getString("ADDRESS");
				
				list.add(new Member_Dto(name, tel, address));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	
	public List<BUSI_Time_Dto> getTime_list(String id){
		String sql = " SELECT * "
				  +  " FROM BUSI_TIME A INNER JOIN MEMBER B "
				  +  " ON  A.ID = B.ID "
		          +  " WHERE B.ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BUSI_Time_Dto> list = new ArrayList<BUSI_Time_Dto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getTime_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getTime_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getTime_list success");
			
			if (rs.next()) {
				
				int i = 1;
				BUSI_Time_Dto dto = new BUSI_Time_Dto(rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++)
						);
				
				list.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public Map<String, Integer> getCate_list(String id){
		String sql = " SELECT * "
				  +  " FROM BUSI_CATE A INNER JOIN MEMBER B "
				  +  " ON  A.ID = B.ID "
		          +  " WHERE B.ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Map<String, Integer> list = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCate_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getCate_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getCate_list success");
			
			
			while(rs.next()) {
		
				list = new HashMap<String, Integer>();
				
				list.put("내과", rs.getInt("INTERNAL"));
				list.put("마취통증학과", rs.getInt("ANPN"));
				list.put("산부인과", rs.getInt("MTRNT"));
				list.put("소아청소년과", rs.getInt("PDTRC"));
				list.put("신경과", rs.getInt("NRLGY"));
				list.put("신경외과", rs.getInt("NRSRG"));
				list.put("심장내과", rs.getInt("CRDLG"));
				list.put("영상의학과", rs.getInt("XRAY"));
				list.put("외과", rs.getInt("GS"));
				list.put("응급의학과", rs.getInt("DPRTM"));
				list.put("정형외과", rs.getInt("OS"));
				list.put("재활의학과", rs.getInt("RHBLT"));
				list.put("흉부심장혈관학과", rs.getInt("THRCC"));
				list.put("피부비뇨기과", rs.getInt("SKIN_URO"));
				list.put("치과", rs.getInt("DENT"));
				list.put("안과", rs.getInt("OPHTH"));
			}
			
			System.out.println(list.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	
	public Map<String, Integer> getAmetiny_list(String id){
		String sql = " SELECT * "
				  +  " FROM BUSI_AMENITY A INNER JOIN MEMBER B "
				  +  " ON  A.ID = B.ID "
		          +  " WHERE B.ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Map<String, Integer> list = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getAmetiny_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getAmetiny_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getAmetiny_list success");
			
			
			while(rs.next()) {
		
				list = new HashMap<String, Integer>();
				
				list.put("주차장", rs.getInt("PARKING"));
				list.put("편의점", rs.getInt("CONV"));
				list.put("ATM,은행", rs.getInt("BANK"));
				list.put("약국", rs.getInt("DRUG"));
				list.put("대중 교통", rs.getInt("BMW"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		System.out.println(list.toString());
		return list;
	}
	
	public String getBreak_Time(String id, String date){
		String sql = " SELECT "+ date +" " 
					+ " FROM BUSI_TIME "
					+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String Break = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBreak_Time success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getBreak_Time success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getBreak_Time success");
			
			if (rs.next()) {
				Break = rs.getString(1);
			}
			System.out.println(Break);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return Break;
	
	}
	
	
	public String getLunch_Time(String id){
		String sql = " SELECT LUNCH " 
					+ " FROM BUSI_TIME "
					+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String lunch = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getLunch_Time success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getLunch_Time success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getLunch_Time success");
			
			if (rs.next()) {
				lunch = rs.getString(1);
			}
			System.out.println(lunch);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return lunch;
	
	}
	
	public String getReserve_Time(String id){
		String sql = " SELECT LUNCH " 
					+ " FROM BUSI_TIME "
					+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String lunch = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve_Time success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getReserve_Time success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserve_Time success");
			
			if (rs.next()) {
				lunch = rs.getString(1);
			}
			System.out.println(lunch);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return lunch;
	
	}
	
	public List<String> getDate_Time(String date, String id){
		String sql = " SELECT RESERVE_TIME " 
					+ " FROM RESERVE "
					+ " WHERE RESERVE_DATE = ? and BUSI_ID = ?  ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String time = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve_Time success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, date);
			psmt.setString(2, id);
			System.out.println("2/6 getReserve_Time success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserve_Time success");
			
			while(rs.next()) {
				time = rs.getString(1);
				
				
				list.add(time);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	
	}
	
}