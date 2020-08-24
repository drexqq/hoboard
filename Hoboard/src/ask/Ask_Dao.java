package ask;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBClose;
import db.DBConnection;

public class Ask_Dao {
	private static Ask_Dao dao = new Ask_Dao();

	private Ask_Dao() {
	}

	public static Ask_Dao getInstance() {
		return dao;
	}

	public List<Ask_Dto> getAskList(String id) {
		String query = " SELECT SEQ, AUTH, ID, TITLE, CONTENT, COMM, WDATE FROM ASK_TABLE " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Ask_Dto> list = new ArrayList<Ask_Dto>();

		try {
			conn = DBConnection.getConnection();
			psmt.setString(1, id);
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Ask_Dto dto = new Ask_Dto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(4));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public int getAskCount(String id, String choice, String searchWord) {
		String sql = " SELECT COUNT(*) FROM ASK_TABLE ";

		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("title")) {
				sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("content")) {
				sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("") || choice == null) {
				sqlWord = "";
			}
			sqlWord += " AND ID = '" + id + "' ";
		}
		else sqlWord += " WHERE ID = '" + id + "' ";
		System.out.println(sql+sqlWord);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
	}

	public List<Ask_Dto> getAskPagingList(String choice, String searchWord, int limit, int page) {
		/*
		 * 1. row 번호 2. 검색 3. 정렬 4. 범위설정 1~10까지
		 */
		String sql = " SELECT SEQ, AUTH, ID, TITLE, CONTENT, COMM, WDATE " + " FROM ";

		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, " + " SEQ, AUTH, ID, TITLE, CONTENT, COMM, WDATE "
				+ " FROM ASK_TABLE ";

		String sqlWord = "";
		if (choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("") || choice == null) {
			sqlWord = "";
		}
		sql = sql + sqlWord;

		sql += " ORDER BY SEQ DESC) "; // 최신 날짜 순으로 정렬
		sql += " WHERE RNUM >= ? AND RNUM <= ? ";

		int start, end;
		start = 1 + limit * page; // 시작 글의 번호
		end = limit + limit * page; // 끝 글의 번호

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<Ask_Dto> list = new ArrayList<Ask_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getAskPagingList success");

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, start);
			psmt.setInt(2, end);

			System.out.println("2/6 getAskPagingList success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getAskPagingList success");

			while (rs.next()) {
				int j = 1;
				Ask_Dto dto = new Ask_Dto(rs.getInt(j++), rs.getInt(j++), rs.getString(j++), rs.getString(j++),
						rs.getString(j++), rs.getString(j++), rs.getString(j++));
				list.add(dto);
			}
			System.out.println("4/6 getAskPagingList success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("6/6 getAskPagingList success");
		return list;
	}

	// LIST => DETAIL로 접근 시 이용 함수
	public Ask_Dto getAskSeq(int seq) {

		String sql = " SELECT SEQ, AUTH, ID, TITLE, CONTENT, COMM, WDATE " + "	FROM ASK_TABLE " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Ask_Dto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1");
			psmt = conn.prepareStatement(sql);
			System.out.println("2");

			psmt.setInt(1, seq);
			System.out.println("3");

			rs = psmt.executeQuery();

			if (rs.next()) {
				dto = new Ask_Dto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setAuth(rs.getInt("AUTH"));
				dto.setId(rs.getString("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setComm(rs.getString("COMM"));
				dto.setWdate(rs.getString("WDATE"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	public boolean ask_write(Ask_Dto dto) {

		String sql = " INSERT INTO ASK_TABLE " + " ( SEQ, AUTH, ID, TITLE, CONTENT, COMM, WDATE )  "
				+ " VALUES( ASK_SEQ.NEXTVAL, 0, ?, ?, ?, '0', SYSDATE ) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ask_write success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/6 ask_write success");

			count = psmt.executeUpdate();
			System.out.println("3/6 ask_write success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// 글 삭제
	public boolean ask_delete(int seq) {

		String sql = " DELETE FROM ASK_TABLE " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ask_delete");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 ask_delete");

			count = psmt.executeUpdate();
			System.out.println("3/6 ask_delete");

			System.out.println(count);

		} catch (Exception e) {
			System.out.println("fail deleteBbs");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count > 0 ? true : false;
	}

	// 업데이트
	public boolean ask_update(int seq, String title, String content) {
		String sql = " UPDATE ASK_TABLE SET " + " TITLE=?, CONTENT=? " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S ask_update");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			System.out.println("2/6 S ask_update");

			count = psmt.executeUpdate();
			System.out.println("3/6 S ask_update");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);

		}
		System.out.println("success");
		return count > 0 ? true : false;

	}

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);
	}

}
