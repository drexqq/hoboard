package Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Util.UtilEx;
import db.DBClose;
import db.DBConnection;
import member.BUSI_Amenity_Dto;
import member.BUSI_Cate_Dto;
import member.BUSI_Member_Dao;
import member.BUSI_Member_Dto;
import member.BUSI_Time_Dto;
import member.Member_Dao;
import member.Member_Dto;
import review.Review_Dto;

public class Reserve_Dao {

	private static Reserve_Dao dao = new Reserve_Dao();
	private Reserve_Dao() {}
	public static Reserve_Dao getInstance() { return dao; }

	// GET RESERVE COUNT --- 사용중
	public int getsearch_allcount(String loc, String amt, String searchWord) {
		String sql = " SELECT COUNT(*) FROM MEMBER WHERE ID IN ";
		String sqlamt = (amt == null || "".equals(amt)) ? "" : " (SELECT ID FROM BUSI_CATE WHERE " + amt + " = 1) ";
		String sqlloc = (loc == null || "".equals(loc)) ? "" : " AND ADDRESS LIKE '%" + loc.trim() + "%' ";
		String sqlword = (searchWord == null || "".equals(searchWord)) ? ""
				: " AND NAME LIKE '%" + searchWord.trim() + "%' ";

		if ((loc == null || "".equals(loc)) && (amt == null || "".equals(amt))
				&& (searchWord == null || "".equals(searchWord))) {
			sql = " SELECT COUNT(*) FROM MEMBER WHERE AUTH = 2 ";
		} else if ((loc != null || !"".equals(loc)) && (amt == null || "".equals(amt))) {
			sql = " SELECT COUNT(*) FROM MEMBER WHERE AUTH = 2 ";
			if (searchWord == null || "".equals(searchWord))
				sql += sqlloc;
			else
				sql += sqlloc + sqlword;
		} else if ((loc == null || "".equals(loc)) && (amt != null || !"".equals(amt))) {
			if (searchWord == null || "".equals(searchWord))
				sql += sqlamt;
			else
				sql += sqlamt + sqlword;
		} else if ((loc != null || !"".equals(loc)) && (amt != null || !"".equals(amt))) {
			if (searchWord == null || "".equals(searchWord))
				sql += sqlamt + sqlloc;
			else
				sql += sqlamt + sqlloc + sqlword;
		}
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int len = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				len = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
	}

	// GET RESERVE LIST --- 사용중
	public List<Member_Dto> getSearch_list(String loc, String amt, String searchWord, int limit, int page) {
		String sql = "";
		String sqlamt = (amt == null || "".equals(amt)) ? ""
				: " (SELECT ID FROM BUSI_CATE WHERE " + amt.trim() + " = 1) ";
		String sqlloc = (loc == null || "".equals(loc)) ? "" : " AND ADDRESS LIKE '%" + loc.trim() + "%' ";
		String sqlword = (searchWord == null || "".equals(searchWord)) ? ""
				: " AND NAME LIKE '%" + searchWord.trim() + "%' ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();

		if ((loc == null || "".equals(loc)) && (amt == null || "".equals(amt))
				&& (searchWord == null || "".equals(searchWord))) {
			sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE AUTH = 2) MEM ";
		} else if ((loc != null || !"".equals(loc)) && (amt == null || "".equals(amt))) {
			if (searchWord == null || "".equals(searchWord)) {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE AUTH = 2 "
						+ sqlloc + " ) MEM ";
			} else {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE AUTH = 2 "
						+ sqlloc + sqlword + " ) MEM ";
			}
		} else if ((loc == null || "".equals(loc)) && (amt != null || !"".equals(amt))) {
			if (searchWord == null || "".equals(searchWord)) {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE ID IN "
						+ sqlamt + " ) MEM ";
			} else {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE ID IN "
						+ sqlamt + sqlword + " ) MEM ";
			}
		} else if ((loc != null || !"".equals(loc)) && (amt != null || !"".equals(amt))) {
			if (searchWord == null || "".equals(searchWord)) {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE ID IN "
						+ sqlamt + sqlloc + " ) MEM ";
			} else {
				sql = " SELECT MEM.* FROM (SELECT ROWNUM AS RNUM, AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM FROM MEMBER WHERE ID IN "
						+ sqlamt + sqlloc + sqlword + " ) MEM ";
			}
		}
		sql += " WHERE MEM.RNUM >= ? AND MEM.RNUM <= ? ";
		int start, end;
		start = 1 + limit * page; // 시작 글의 번호
		end = limit + limit * page; // 끝 글의 번호
		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			rs = psmt.executeQuery();

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

	// GET ALL LIST RESERVE --- 사용중
	public int getUserReserveCount(String choice, String searchWord, String id, int auth) {
		String column = "";
		String name = "";
		if (auth == 2) {
			column = "BUSI_ID";
			name = "INDVD_ID";
		} else {
			column = "INDVD_ID";
			name = "BUSI_ID";
		}
		String query = " SELECT COUNT(*) FROM RESERVE WHERE " + column + " = '" + id + "' ";
		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("name"))
				sqlWord = " AND " + name + " LIKE " + "( SELECT ID FROM MEMBER WHERE NAME LIKE '%" + searchWord.trim()
						+ "%' )";
			else if (choice.equals("cate"))
				sqlWord = " AND CATE LIKE '%" + searchWord.trim() + "%' ";
		}
		query += sqlWord;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return count;
	}

	// GET ALL LIST USER'S RESERVE --- 사용중
	public List<LinkedHashMap<Reserve_Dto, String>> getUserReserveList(String choice, String searchWord, String id, int auth) {
		String column = "";
		String name = "";
		if (auth == 2) {
			column = "BUSI_ID";
			name = "INDVD_ID";
		} else {
			column = "INDVD_ID";
			name = "BUSI_ID";
		}
		String query = " SELECT R.*, M.NAME FROM RESERVE R INNER JOIN MEMBER M ON M.ID = R."+name+" WHERE " + column + " = ? ";
		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("name"))
				sqlWord = " AND " + name + " LIKE " + "( SELECT ID FROM MEMBER WHERE NAME LIKE '%" + searchWord.trim()
						+ "%' )";
			else if (choice.equals("cate"))
				sqlWord = " AND CATE LIKE '%" + searchWord.trim() + "%' ";
		}
		query += sqlWord;
		LinkedHashMap<Reserve_Dto, String> map = new LinkedHashMap<Reserve_Dto, String>();
		List<LinkedHashMap<Reserve_Dto, String>> list = new ArrayList<LinkedHashMap<Reserve_Dto, String>>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			int j = 1;
			while (rs.next()) {
				int i = 1;
				Reserve_Dto dto = new Reserve_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getString(i++),
						UtilEx.dateToTimestamp(rs.getString(i++)));
				map.put(dto, rs.getString(i++));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	// GET BUSI_USER DETAILS
	public Map<String, Object> getBusiUserDetail (String id) {
		String query = " SELECT"
						+ " NAME, TEL, ADDRESS, D_ADDRESS, HOMEPAGE, LOGO,"
						+ " ROUND((SELECT NVL(AVG(SCORE), 0) FROM REVIEW WHERE BUSI_ID = M.ID)) AS SCROE, "
						+ " T.MON, T.TUE, T.WED, T.THU, T.FRI, T.SAT, T.SUN, T.LUNCH, T.HOLIDAY, T.NIGHT, T.EMER, "
						+ " A.PARKING, A.CONV, A.BANK, A.DRUG, A.BMW, C.* "
						+ " FROM MEMBER M "
						+ " INNER JOIN BUSI_MEMBER B ON M.ID = B.ID"
						+ " INNER JOIN BUSI_TIME T ON B.ID = T.ID "
						+ " INNER JOIN BUSI_AMENITY A ON T.ID = A.ID "
						+ " INNER JOIN BUSI_CATE C ON A.ID = C.ID "
						+ " WHERE M.ID = ? ";
		
		Member_Dto memDto = null;
		BUSI_Member_Dto bmemDto = null;
		Review_Dto reviewDto = null;
		BUSI_Time_Dto timeDto = null;
		BUSI_Amenity_Dto amenityDto = null;
		BUSI_Cate_Dto cateDto = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				int i = 1;
				memDto = new Member_Dto(
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++)
						);
				bmemDto = new BUSI_Member_Dto(
							rs.getString(i++),
							rs.getString(i++)
						);
				reviewDto = new Review_Dto(
							rs.getInt(i++)
						);
				timeDto = new BUSI_Time_Dto(
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getString(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getInt(i++)
						);
				List<String> amenityList = new ArrayList<String>();
				for (int j = 0; j < 5; j++) {
					amenityList.add(rs.getString(i++));
				}
//				amenityDto = new BUSI_Amenity_Dto(
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++)
//						);
				List<String> cateList = new ArrayList<String>();
				for (int j = 0; j < 17; j++) {
					cateList.add(rs.getString(i++));
				}
				cateList.remove(0);
//				cateDto = new BUSI_Cate_Dto(
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++),
//							rs.getString(i++)
//						);
				map.put("memDto", memDto);
				map.put("bmemDto", bmemDto);
				map.put("reviewDto", reviewDto);
				map.put("timeDto", timeDto);
				map.put("amenityList", amenityList);
				map.put("cateList", cateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return map;
	}
	
	// INSERT RESERVE
	public boolean addReserve(Reserve_Dto reserveDto) {
		String query = " INSERT INTO RESERVE "
					+ " VALUES( SEQ_RESERVE.NEXTVAL, ?, ?, ?, ?, 0, ?, SYSDATE ) ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, reserveDto.getBusi_id());
			psmt.setString(2, reserveDto.getIndvd_id());
			psmt.setString(3, reserveDto.getCate());
			psmt.setString(4, reserveDto.getCont());
			psmt.setString(5, reserveDto.getReserve_time());
			count = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}
	
	// UPDATE RESERVE STATUS = 2
	public boolean updateReserve(int seq) {
		String query = " UPDATE RESERVE SET STATUS = 2 WHERE RESERVE_SEQ = ? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, seq);
			count = psmt.executeUpdate();
			System.out.println("update complete");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}
	
	// UPDATE RESERVE STATUS = 3
	public boolean doneReserve(int seq) {
		String query = " UPDATE RESERVE SET STATUS = 3 WHERE RESERVE_SEQ = ? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, seq);
			count = psmt.executeUpdate();
			System.out.println("update complete");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// GET RESERVE
	public HashMap<String, Reserve_Dto> getReserve(int seq) {
		String sql = " SELECT R.*, M.NAME "
				   + " FROM RESERVE R "
				   + " INNER JOIN MEMBER M ON M.ID = R.BUSI_ID "
				   + " WHERE RESERVE_SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		HashMap<String, Reserve_Dto> map = new HashMap<String, Reserve_Dto>();
		Reserve_Dto dto = null;
		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			rs = psmt.executeQuery();
			if (rs.next()) {
				int i = 1;
				dto = new Reserve_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++));
				map.put(rs.getString(i++), dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return map;
	}
	/*
	public List<Member_Dto> getCate_list(String cate, int num) {
		String sql = " SELECT AUTH, ID, NAME, TEL, EMAIL, ADDRESS, D_ADDRESS, POST_NUM " + " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE " + cate + " = ? ) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();

		try {

			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);

			rs = psmt.executeQuery();
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
		String sql = " SELECT AVG(A.SCORE) " + " FROM REVIEW A INNER JOIN MEMBER B " + " ON  A.BUSI_ID = B.ID "
				+ " WHERE B.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int score = 0;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next())
				score = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return score;
	}

	public String getHomepage(String name) {
		String sql = " SELECT HOMEPAGE " + " FROM BUSI_MEMBER A INNER JOIN MEMBER B " + " ON  A.ID = B.ID "
				+ " WHERE B.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String home = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);

			rs = psmt.executeQuery();

			if (rs.next())
				home = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return home;

	}

	public List<Member_Dto> getMember_list(String id) {
		String sql = " SELECT NAME , TEL , ADDRESS " + " FROM MEMBER " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

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

	public List<BUSI_Time_Dto> getTime_list(String id) {
		String sql = " SELECT * " + " FROM BUSI_TIME A INNER JOIN MEMBER B " + " ON  A.ID = B.ID " + " WHERE B.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BUSI_Time_Dto> list = new ArrayList<BUSI_Time_Dto>();

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				int i = 1;
				BUSI_Time_Dto dto = new BUSI_Time_Dto(rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public Map<String, Integer> getCate_list(String id) {
		String sql = " SELECT * " + " FROM BUSI_CATE A INNER JOIN MEMBER B " + " ON  A.ID = B.ID " + " WHERE B.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Map<String, Integer> list = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			while (rs.next()) {

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public Map<String, Integer> getAmetiny_list(String id) {
		String sql = " SELECT * " + " FROM BUSI_AMENITY A INNER JOIN MEMBER B " + " ON  A.ID = B.ID "
				+ " WHERE B.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Map<String, Integer> list = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			while (rs.next()) {

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
		return list;
	}

	public String getBreak_Time(String id, String date) {
		String sql = " SELECT " + date + " " + " FROM BUSI_TIME " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String Break = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next())
				Break = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return Break;
	}

	public String getLunch_Time(String id) {
		String sql = " SELECT LUNCH " + " FROM BUSI_TIME " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String lunch = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next())
				lunch = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return lunch;
	}

	public String getReserve_Time(String id) {
		String sql = " SELECT LUNCH " + " FROM BUSI_TIME " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String lunch = null;

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next())
				lunch = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return lunch;
<<<<<<< HEAD
=======

	}

	public List<String> getDate_Time(String date, String id) {
		String sql = " SELECT RESERVE_TIME " + " FROM RESERVE " + " WHERE RESERVE_DATE = ? and BUSI_ID = ?  ";

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

			while (rs.next()) {
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

	// GET ALL LIST RESERVE
	public int getUserReserveCount(String choice, String searchWord, String id, int auth) {
		String column = "";
		String name = "";
		if (auth == 2) {
			column = "BUSI_ID";
			name = "INDVD_ID";
		} else {
			column = "INDVD_ID";
			name = "BUSI_ID";
		}

		String query = " SELECT COUNT(*) FROM RESERVE WHERE " + column + " = '" + id + "' ";
		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("name"))
				sqlWord = " AND " + name + " LIKE " + "( SELECT ID FROM MEMBER WHERE NAME LIKE '%" + searchWord.trim()
						+ "%' )";
			else if (choice.equals("cate"))
				sqlWord = " AND BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
		}
		query += sqlWord;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return count;
	}

	// GET ALL LIST USER'S RESERVE
	public List<LinkedHashMap<Reserve_Dto, String>> getUserReserveList(String choice, String searchWord, String id, int auth){
		String column = "";
		String name = "";
		if(auth == 2) {
			column = "BUSI_ID";
			name = "INDVD_ID";
		}
		else {
			column = "INDVD_ID";
			name = "BUSI_ID";
		}
		String query = " SELECT * FROM RESERVE WHERE "+column+" = ? ";
		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("name"))		sqlWord = " AND "+name+" LIKE " + "( SELECT ID FROM MEMBER WHERE NAME LIKE '%"+searchWord.trim()+"%' )";
			else if (choice.equals("cate"))	sqlWord = " AND BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
		}
		query += sqlWord;
		
		LinkedHashMap<Reserve_Dto, String> map = new LinkedHashMap<Reserve_Dto, String>();
		List<LinkedHashMap<Reserve_Dto, String>> list = new ArrayList<LinkedHashMap<Reserve_Dto, String>>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			int j = 1;
			while (rs.next()) {
				int i = 1;
				Reserve_Dto dto = new Reserve_Dto(
										rs.getInt(i++),
										rs.getString(i++),
										rs.getString(i++),
										rs.getString(i++),
										rs.getString(i++),
										rs.getInt(i++),
										rs.getString(i++), 
										UtilEx.dateToTimestamp(rs.getString(i++)));
				map.put(dto, Member_Dao.getInstance().getUser(rs.getString(2)).getName());
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public List<Reserve_Dto> getReserve_list(String id) {
		String sql = " SELECT RESERVE_DATE , RESERVE_TIME " + " FROM RESERVE " + " WHERE BUSI_ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Reserve_Dto> list = new ArrayList<Reserve_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/6 getReserve_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserve_list success");

			while (rs.next()) {
				Reserve_Dto dto = new Reserve_Dto();
				int i = 1;
				dto.setReserve_date(rs.getString(i++));
				dto.setReserve_time(rs.getString(i++));
				
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
>>>>>>> 87ee37b9eb5e5bb8fd36b5fdd70f1451b5a5e4cd
	}
	*/
	/*
	 * public List<String> getDate_Time(String date, String id) { String sql =
	 * " SELECT RESERVE_TIME " + " FROM RESERVE " +
	 * " WHERE RESERVE_DATE = ? and BUSI_ID = ?  ";
	 * 
	 * Connection conn = null; PreparedStatement psmt = null; ResultSet rs = null;
	 * List<String> list = new ArrayList<String>(); String time = null;
	 * 
	 * try { conn = DBConnection.getConnection();
	 * 
	 * psmt = conn.prepareStatement(sql); psmt.setString(1, date); psmt.setString(2,
	 * id);
	 * 
	 * rs = psmt.executeQuery();
	 * 
	 * while (rs.next()) { time = rs.getString(1); list.add(time); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { DBClose.close(psmt, conn,
	 * rs); } return list; }
	 */

	/*
	 * public List<Reserve_Dto> getReserve_list(String id) { String sql =
	 * " SELECT RESERVE_DATE , RESERVE_TIME " + " FROM RESERVE " +
	 * " WHERE BUSI_ID = ? ";
	 * 
	 * Connection conn = null; PreparedStatement psmt = null; ResultSet rs = null;
	 * List<Reserve_Dto> list = new ArrayList<Reserve_Dto>();
	 * 
	 * try { conn = DBConnection.getConnection();
	 * 
	 * psmt = conn.prepareStatement(sql); psmt.setString(1, id);
	 * 
	 * rs = psmt.executeQuery();
	 * 
	 * while (rs.next()) { Reserve_Dto dto = new Reserve_Dto(); int i = 1;
	 * dto.setReserve_date(rs.getString(i++));
	 * dto.setReserve_time(rs.getString(i++)); list.add(dto); } } catch (Exception
	 * e) { e.printStackTrace(); } finally { DBClose.close(psmt, conn, rs); } return
	 * list; }
	 */
}