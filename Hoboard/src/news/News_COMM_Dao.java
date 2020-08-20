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

public class news_comm_dao {

	private static news_comm_dao dao = new news_comm_dao();
	
	private news_comm_dao() {
	}

	public static news_comm_dao getInstance() {
		return dao;
	}
	
	// TODO insert comment
<<<<<<< HEAD:Hoboard/src/news/News_COMM_Dao.java
	public boolean comm_write(News_COMM_Dto dto) {
=======
	public boolean comm_write(news_comm_dto dto2) {
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c:Hoboard/src/news/news_comm_dao.java

		String sql = " INSERT INTO NEWS_COMM " 
		+ " VALUES( SEQ_NEWS_COMM.NEXTVAL, ?, 'admin', ?, SYSDATE ) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 insertComment success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto2.getB_seq());
			//psmt.setString(2, dto.getId());
			psmt.setString(2, dto2.getContent());
			
			System.out.println("2/6 insertComment success");

			count = psmt.executeUpdate();

			System.out.println("3/6 insertComment success");

		} catch (Exception e) {
			try {
                conn.rollback(); // error rollback
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	
<<<<<<< HEAD:Hoboard/src/news/News_COMM_Dao.java
	public List<News_COMM_Dto> getComm(int c_seq) {
=======
	public List<news_comm_dto> getComm(int b_seq) {
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c:Hoboard/src/news/news_comm_dao.java
		
		String sql = " SELECT * from news_comm where b_seq = ? ";
//				   + " FROM NEWS A INNER JOIN NEWS_COMM B "
//				   + " ON A.NEWS_SEQ = ? "
//				   + " ORDER BY C_SEQ DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
<<<<<<< HEAD:Hoboard/src/news/News_COMM_Dao.java
		ArrayList<News_COMM_Dto> list = new ArrayList<News_COMM_Dto>();
=======
		ArrayList<news_comm_dto> clist = new ArrayList<news_comm_dto>();
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c:Hoboard/src/news/news_comm_dao.java
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 selectComments success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, b_seq);
			System.out.println("2/6 selectComments success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int i = 1;
<<<<<<< HEAD:Hoboard/src/news/News_COMM_Dao.java
				News_COMM_Dto dto = new News_COMM_Dto(rs.getInt(i++), 
														rs.getString(i++), 
														rs.getString(i++), 
														rs.getString(i++),
														rs.getInt(i++));				
				list.add(dto);
=======
				news_comm_dto dto2 = new news_comm_dto ( rs.getInt(i++), 
														 rs.getString(i++),
														 rs.getString(i++), 
														 rs.getString(i++), 
														 rs.getInt(i++));				
				clist.add(dto2);
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c:Hoboard/src/news/news_comm_dao.java
				
			}
			System.out.println(clist.toString());
			System.out.println("3/6 selectComments success");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return clist;
	}	
	
	public boolean comm_delete(int c_seq) {
		
		String sql = " DELETE FROM NEWS_COMM "
					+ " WHERE C_SEQ = ? ";
		
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
		
		return count>0?true:false;
	}
	
	public News_COMM_Dto getCseq( int c_seq ) {
		
		String sql = " SELECT * FROM "
				+ " NEWS_COMM "
				+ " WHERE C_SEQ= ? ";
		
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
			
			while(rs.next()){
<<<<<<< HEAD:Hoboard/src/news/News_COMM_Dao.java
				dto = new News_COMM_Dto();
=======
				dto = new news_comm_dto();
				dto.setC_seq(rs.getInt("c_seq"));
>>>>>>> ed1ef60d2645bdc5006a79ceb88a72bda390f38c:Hoboard/src/news/news_comm_dao.java
				dto.setB_seq(rs.getInt("b_seq"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("wdate"));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);
		}		
		
		return dto;	
	}	
	
	public boolean comm_update(int c_seq, String content) {
		String sql = " UPDATE NEWS_COMM SET "
				+ " CONTENT= ? "
				+ " WHERE C_SEQ= ? ";
		
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
		} finally{
			DBClose.close(psmt, conn, null);
		}		
		
		return count>0?true:false;
	}	
	

	
}
