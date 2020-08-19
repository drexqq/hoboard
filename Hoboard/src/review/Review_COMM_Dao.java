package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class Review_COMM_Dao {

	private static Review_COMM_Dao dao = new Review_COMM_Dao();

	private Review_COMM_Dao() {
	}

	public static Review_COMM_Dao getInstance() {
		return dao;
	}

	// TODO insert comment
	public boolean insertComment(Review_COMM_Dto dto) {

		String sql = " INSERT INTO REVIEW_COMM " 
		+ " VALUES(SEQ_REVIEW_COMM.NEXTVAL, ?, ?, ?, SYSDATE) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 insertComment success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getBoard_no());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getContent());

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
	
	
	public List<Review_COMM_Dto> getComments(int boardseq) {
		
		String sql = " SELECT B.SEQ , B.BOARD_NO , B.ID , B.CONTENT , B.COMM_DATE "
				   + " FROM REVIEW A INNER JOIN REVIEW_COMM B "
				   + " ON A.REVIEW_SEQ = ? AND B.BOARD_NO=A.REVIEW_SEQ "
				   + " ORDER BY B.SEQ DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ArrayList<Review_COMM_Dto> list = new ArrayList<Review_COMM_Dto>();
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 selectComments success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardseq);
			System.out.println("2/6 selectComments success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int i = 1;
				Review_COMM_Dto dto = new Review_COMM_Dto(rs.getInt(i++), 
														rs.getInt(i++), 
														rs.getString(i++), 
														rs.getString(i++), 
														rs.getString(i++));				
				list.add(dto);
				
				
				/*
				 * Review_COMM_Dto dto = new Review_COMM_Dto(); dto.setSeq(rs.getInt("SEQ"));
				 * dto.setBoard_no(rs.getInt("BOARD_NO")); dto.setId(rs.getString("ID"));
				 * dto.setContent(rs.getString("CONTENT")); dto.setDate(rs.getString("DATE"));
				 * list.add(dto);
				 */
			}
			System.out.println(list.toString());
			System.out.println("3/6 selectComments success");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean Comment_delete(int seq) {
		
		String sql = " DELETE FROM REVIEW_COMM "
					+ " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Review_COMM_Dto dto = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 Comment_delete success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
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
	
	public Review_COMM_Dto getOne_Comment( int seq ) {
		String sql = " SELECT * FROM "
				+ " REVIEW_COMM "
				+ " WHERE SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Review_COMM_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getOne_Comment success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getOne_Comment success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getOne_Comment success");
			
			while(rs.next()){
				dto = new Review_COMM_Dto();
				dto.setSeq(rs.getInt("seq"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getString("COMM_DATE"));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);
		}		
		
		return dto;	
	}
	
	public boolean Comment_update(int seq, String content) {
		String sql = " UPDATE REVIEW_COMM SET "
				+ " CONTENT= ? "
				+ " WHERE SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 Comment_update success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, seq);
			
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
