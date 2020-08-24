package news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import review.Review_COMM_Dao;
import review.Review_COMM_Dto;

public class News_COMM_Dao {

	private static News_COMM_Dao dao = new News_COMM_Dao();

	private News_COMM_Dao() {
	}

	public static News_COMM_Dao getInstance() {
		return dao;
	}

	// TODO insert comment
	public boolean comm_write(News_COMM_Dto dto) {
		
		String sql = " INSERT INTO NEWS_COMM " 
				+ " VALUES(SEQ_NEWS_COMM.NEXTVAL, ?, ?, ?, SYSDATE) ";

		System.out.println(dto.toString());
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 insertComment success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getB_seq());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getContent());
			System.out.println("2/6 insertComment success");
			count = psmt.executeUpdate();
			System.out.println("3/6 insertComment success");

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // error rollback
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	public List<News_COMM_Dto> getComm(int b_seq) {

		String sql = " SELECT " + " B_SEQ, C_SEQ, ID, CONTENT, WDATE " + " from news_comm where b_seq = ? "
				+ " ORDER BY C_SEQ DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<News_COMM_Dto> list = new ArrayList<News_COMM_Dto>();
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getComm success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, b_seq);
			System.out.println("2/6 getComm success");

			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				News_COMM_Dto dto = new News_COMM_Dto(rs.getInt(i++), rs.getInt(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++));
				list.add(dto);
			}
			System.out.println(list.toString());
			System.out.println("3/6 getComm");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

	public boolean comm_delete(int c_seq) {

		String sql = " DELETE FROM NEWS_COMM " + " WHERE C_SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Review_COMM_Dto dto = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 Comment_delete success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, c_seq);
			System.out.println("2/6 Comment_delete success");

			count = psmt.executeUpdate();
			System.out.println("3/6 Comment_delete success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}

	public News_COMM_Dto getCseq(int c_seq) {

		String sql = " SELECT * FROM " + " NEWS_COMM " + " WHERE C_SEQ= ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		News_COMM_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCseq success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, c_seq);
			System.out.println("2/6 getCseq success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getCseq success");

			while (rs.next()) {
				dto = new News_COMM_Dto();
				dto.setC_seq(rs.getInt("c_seq"));
				dto.setB_seq(rs.getInt("b_seq"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("wdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return dto;
	}

	public boolean comm_update(int c_seq, String content) {
		String sql = " UPDATE NEWS_COMM SET " + " CONTENT= ? " + " WHERE C_SEQ= ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 Comment_update success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, c_seq);

			System.out.println("2/6 Comment_update success");

			count = psmt.executeUpdate();
			System.out.println("3/6 Comment_update success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}

}
