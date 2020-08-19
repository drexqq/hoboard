package review;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import Reserve.Reserve_Dto;
import db.DBClose;
import db.DBConnection;
import member.Member_Dto;

public class Review_Dao {

	private static Review_Dao dao = new Review_Dao();

	private Review_Dao() {

	}

	public static Review_Dao getInstance() {
		return dao;
	}

	// TODO all list SELECT
	public List<Review_Dto> getReviewList() {

		String sql = " SELECT * FROM REVIEW "
				+ " ORDER BY REVIEW_SEQ DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<Review_Dto> list = new ArrayList<Review_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReviewList success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getReviewList success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReviewList success");

			while (rs.next()) {
				int i = 1;
				Review_Dto dto = new Review_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++));
				list.add(dto);
			}
			System.out.println("4/6 getReviewList success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	// TODO all list INSERT
	public boolean writeReview(Review_Dto dto) {

		String sql = "INSERT INTO REVIEW " + " ( REVIEW_SEQ, BUSI_ID, INDVD_ID, "
				+ " TITLE, CONTENT, VIEWCOUNT, SCORE, WDATE, FILENAME, BUSI_CATE, DEL ) "
				+ " VALUES( SEQ_REVIEW.NEXTVAL, ?, ?, " + " ?, ?, 0, ?, SYSDATE, ?, ?, 0 )";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 wirteReview success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getBusi_id());
			psmt.setString(2, dto.getIndvd_id());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getScore());
			psmt.setString(6, dto.getFilename());
			psmt.setString(7, dto.getBusi_cate());
			System.out.println("2/6 wirteReview success");

			count = psmt.executeUpdate();
			System.out.println("3/6 wirteReview success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// TODO status check
	public String checkStatus(String id, int seq) {

		String sql = " SELECT STATUS " + " FROM RESERVE " + " WHERE INDVD_ID = ? AND RESERVE_SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String check = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 checkStatus success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, seq);
			System.out.println("2/6 checkStatus success");

			rs = psmt.executeQuery();
			System.out.println("3/6 checkStatus success");

			if (rs.next()) {
				check = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return check;
	}

	// TODO ALL RESERVE DATA
	public Reserve_Dto getReserve_Data(String id, int seq) {

		String sql = " SELECT RESERVE_SEQ, BUSI_ID, INDVD_ID, CATE, " + " RESERVE_TIME, CONT, STATUS, RESERVE_DATE "
				+ " FROM RESERVE " + " WHERE INDVD_ID=? AND RESERVE_SEQ =? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Reserve_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserveData success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, seq);
			System.out.println("2/6 getReserveData success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserveData success");

			while (rs.next()) {
				int i = 1;
				dto = new Reserve_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getString(i++));
			}
			System.out.println("4/6 getReserveData success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	// TODO get BUSI_member ID
	public Member_Dto getBusi_Name(String busi, int seq) {

		String sql = " SELECT A.NAME " + " FROM MEMBER A INNER JOIN RESERVE B " + " ON A.ID =? AND B.RESERVE_SEQ =? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Member_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserveData success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, busi);
			psmt.setInt(2, seq);
			System.out.println("2/6 getReserveData success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserveData success");

			while (rs.next()) {
				int i = 1;
				dto = new Member_Dto();
				dto.setName(rs.getString(i));
			}
			System.out.println("4/6 getReserveData success");

			/*
			 * for (Member_Dto m : list) { System.out.println(m.toString()); }
			 * System.out.println("5/6 getReserveData success");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return dto;
	}

	// TODO get INDVD_member CATEGORY
	public Reserve_Dto getReserve_Cate(String id, int seq) {

		String sql = " SELECT CATE " + " FROM RESERVE " + " WHERE INDVD_ID = ? and RESERVE_SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Reserve_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve_Cate success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, seq);
			System.out.println("2/6 getReserve_Cate success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getReserve_Cate success");

			while (rs.next()) {
				int i = 1;
				dto = new Reserve_Dto();
				dto.setCate(rs.getString(i));

			}
			System.out.println("4/6 getReserveData success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return dto;
	}

	// TODO Detail list
	public Review_Dto getDetail_list(int seq) {
		String sql = " SELECT * " + " FROM REVIEW " + " WHERE REVIEW_SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Review_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getDetail_list success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getDetail_list success");

			psmt.setInt(1, seq);

			rs = psmt.executeQuery();
			System.out.println("3/6 getDetail_list success");

			if (rs.next()) {
				int i = 1;
				dto = new Review_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++));
			}
			System.out.println("4/6 getDetail_list success");

		} catch (Exception e) {
			System.out.println("getDetail_list fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;

	}

	// TODO update
	public boolean getReview_update(int seq, String title, String content) {
		String sql = " UPDATE REVIEW SET " + " TITLE= ?, CONTENT= ? " + " WHERE REVIEW_SEQ= ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReview_update updateBbs");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			System.out.println("2/6 getReview_update updateBbs");

			count = psmt.executeUpdate();
			System.out.println("3/6 getReview_update updateBbs");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}

	// TODO viewcount
	public void viewcount(int seq) {
		String sql = "  UPDATE REVIEW " + "	SET VIEWCOUNT=VIEWCOUNT+1 " + " WHERE REVIEW_SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 viewcount success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 viewcount success");

			psmt.executeUpdate();
			System.out.println("3/6 viewcount success");

		} catch (Exception e) {
			System.out.println("readcount fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	// TODO delete
	public boolean Review_delete(int seq) {

		String sql = " UPDATE REVIEW " + " SET DEL=1 " + " WHERE REVIEW_SEQ= ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 Review_delete Review_delete");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 Review_delete Review_delete");

			count = psmt.executeUpdate();
			System.out.println("3/6 Review_delete Review_delete");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}

	// TODO search all write count
	public int getsearch(String choice, String searchWord) {

		String sql = " SELECT COUNT(*) FROM REVIEW ";

		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("id")) {
				sqlWord = " WHERE INDVD_ID='" + searchWord.trim() + "'";
			} else if (choice.equals("busi_name")) {
				sqlWord = " WHERE BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("title")) {
				sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("content")) {
				sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("score")) {
				sqlWord = " WHERE SCORE='" + searchWord.trim() + "'";
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
			if (rs.next()) {
				len = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getsearch fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("search done");
		return len;
	}

	// TODO Paging NERMU URYUWAR
	public List<Review_Dto> getReview_PagingList(String choice, String searchWord, int limit, int page) {

		String sql = " SELECT REVIEW_SEQ, BUSI_ID, INDVD_ID, " + " TITLE, CONTENT, VIEWCOUNT, SCORE, WDATE, "
				+ " FILENAME, BUSI_CATE, DEL " + " FROM ";

		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY REVIEW_SEQ DESC) AS RNUM, "
				+ " REVIEW_SEQ, BUSI_ID, INDVD_ID, TITLE, CONTENT, VIEWCOUNT, SCORE, WDATE, "
				+ " FILENAME, BUSI_CATE, DEL " + " FROM REVIEW ";

		String sqlWord = "";
		if (choice.equals("id")) {
			sqlWord = " WHERE INDVD_ID='" + searchWord.trim() + "'";
		} else if (choice.equals("busi_name")) {
			sqlWord = " WHERE BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("score")) {
			sqlWord = " WHERE SCORE='" + searchWord.trim() + "'";
		} else if (choice.equals("sel")) {
			sqlWord = "WHERE DEL= 0 ";
		}
		sql = sql + sqlWord;

		sql += " ORDER BY REVIEW_SEQ DESC) ";
		sql += " WHERE RNUM >= ? AND RNUM <= ? ";

		int start, end;
		start = 1 + limit * page; // 시작 글의 번호
		end = limit + limit * page; // 끝 글의 번호

		System.out.println(start + "start");
		System.out.println(end + "end");

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<Review_Dto> list = new ArrayList<Review_Dto>();

		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);

			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				Review_Dto dto = new Review_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getInt(i++));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("get list done");
		return list;
	}

}
