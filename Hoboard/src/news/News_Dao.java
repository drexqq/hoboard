package news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class News_Dao {

	private static News_Dao n_dao = new News_Dao();

	public static News_Dao getInstance() {
		return n_dao;
	}

	public List<News_Dto> getNewsList() {

		String sql = " SELECT NEWS_SEQ, ID, TITLE, CONTENT, VIEWCOUNT, WDATE " + " FROM NEWS ";
		// + " ORDER BY WDATE ASC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<News_Dto> list = new ArrayList<News_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 nlist success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 nlist success");

			rs = psmt.executeQuery();

			while (rs.next()) {
				int j = 1;
				News_Dto dto = new News_Dto(rs.getInt(j++), rs.getString(j++), rs.getString(j++), rs.getString(j++),
						rs.getInt(j++), rs.getString(j++));
				list.add(dto);
			}
			System.out.println("3/6 nlist success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBClose.close(psmt, conn, rs);
			System.out.println("nlist done");

		}
		return list;
	}

	// LIST => DETAIL로 접근 시 이용 함수
	public News_Dto getNewsSeq(int seq) {

		String sql = " SELECT NEWS_SEQ, ID, TITLE, CONTENT, VIEWCOUNT, WDATE " + "	FROM NEWS" + " WHERE NEWS_SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		News_Dto ndto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1");
			psmt = conn.prepareStatement(sql);
			System.out.println("2");

			psmt.setInt(1, seq);
			System.out.println("3");

			rs = psmt.executeQuery();

			if (rs.next()) {
				ndto = new News_Dto();
				ndto.setNews_seq(rs.getInt("NEWS_SEQ"));
				ndto.setId(rs.getString("ID"));
				ndto.setTitle(rs.getString("TITLE"));
				ndto.setContent(rs.getString("CONTENT"));
				ndto.setViewcount(rs.getInt("VIEWCOUNT"));
				ndto.setDate(rs.getString("WDATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return ndto;
	}

	// 글쓰기
	public boolean news_write(News_Dto dto) {

		String sql = " INSERT INTO NEWS " + " ( NEWS_SEQ, ID, TITLE, CONTENT,VIEWCOUNT, WDATE, NEWS_FILE)  "
				+ " VALUES( SEQ_NEWS.NEXTVAL, ?, ?, ?, 0, SYSDATE, ?) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 news_write success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getNews_file());
			System.out.println("2/6 news_write success");

			count = psmt.executeUpdate();
			System.out.println("3/6 news_write success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}
	
	// 글쓰기
	public boolean news_file(News_Dto dto) {
		String sql = " INSERT INTO NEWS " + " ( NEWS_SEQ, ID, TITLE, CONTENT,VIEWCOUNT, WDATE, NEWS_FILE)  "
				+ " VALUES( SEQ_NEWS.NEXTVAL, ?, ?, ?, 0, SYSDATE, ? ) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 news_file success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getNews_file());
			System.out.println("2/6 news_file success");

			count = psmt.executeUpdate();
			System.out.println("3/6 news_file success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// 글 삭제
	public boolean news_delete(int seq) {

		String sql = " DELETE FROM NEWS " + " WHERE NEWS_SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S deleteBbs");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 S deleteBbs");

			count = psmt.executeUpdate();
			System.out.println("3/6 S deleteBbs");

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
	public boolean news_update(int seq, String title, String content) {
		String sql = " UPDATE NEWS SET " + " TITLE=?, CONTENT=? " + " WHERE NEWS_SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S news_update");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			System.out.println("2/6 S news_update");

			count = psmt.executeUpdate();
			System.out.println("3/6 S news_update");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);

		}
		System.out.println("success");
		return count > 0 ? true : false;
	}

	// 조회수
	public boolean viewcount(int seq) {

		boolean vc = false;

		String sql = " UPDATE NEWS " + "	SET VIEWCOUNT=VIEWCOUNT+1 " + " WHERE NEWS_SEQ=? ";

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
			System.out.println("viewcount fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return vc;
	}

	// 서치
	public List<News_Dto> getnews_list(String choice, String searchWord) {

		String sql = " SELECT NEWS_SEQ, ID, TITLE, CONTENT, VIEWCOUNT, WDATE" + " FROM NEWS "
				+ " ORDER BY NEWS_SEQ DESC";

		String sqlWord = "";
		if (choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' AND DEL=0 ";
		} else if (choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		}
		sql = sql + sqlWord;

		//sql += " ORDER BY REF DESC, STEP ASC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<News_Dto> list = new ArrayList<News_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBbsList success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsList success");

			while (rs.next()) {
				int i = 1;
				News_Dto dto = new News_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getInt(i++), rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/6 getBbsList success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	// 검색 한 글의 모든 개수
	public int getAllNews(String choice, String searchWord) {

		String sql = " SELECT COUNT(*) FROM NEWS";

		String sqlWord = "";
		if (choice != null || searchWord != null) {
			if (choice.equals("title")) {
				sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
			} else if (choice.equals("content")) {
				sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
	}

	// 페이징
	public List<News_Dto> getNewsPagingList(String choice, String searchWord, int limit, int page) {

		/*
		 * 1. row 번호 2. 검색 3. 정렬 4. 범위설정 1~10까지
		 */
		String sql = " SELECT NEWS_SEQ, ID, TITLE, CONTENT, VIEWCOUNT, WDATE " + " FROM ";

		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY NEWS_SEQ DESC) AS RNUM, "
				+ " NEWS_SEQ, ID, TITLE, CONTENT, VIEWCOUNT, WDATE " + " FROM NEWS ";

		String sqlWord = "";

		if (choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		}
		sql = sql + sqlWord;

		sql += " ORDER BY NEWS_SEQ DESC) "; // 최신 날짜 순으로 정렬
		sql += " WHERE RNUM >= ? AND RNUM <= ?";

		int start, end;
		start = 1 + limit * page;
		end = limit + limit * page;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<News_Dto> list = new ArrayList<News_Dto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getNewsPagingList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			System.out.println("2/6 getNewsPagingList success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getNewsPagingList success");

			while (rs.next()) {
				int j = 1;
				News_Dto dto = new News_Dto(rs.getInt(j++), rs.getString(j++), rs.getString(j++), rs.getString(j++),
						rs.getInt(j++), rs.getString(j++));
				list.add(dto);
			}
			System.out.println("4/6 getNewsPagingList success");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

}
