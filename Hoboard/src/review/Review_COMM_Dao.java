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
		+ " VALUES(REVIEW_COMM_SEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		boolean result = false;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 insertComment success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getBoard_no());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getContent());

			System.out.println("2/6 insertComment success");

			int count = psmt.executeUpdate();

			if(count > 0) {
				result = true;
				conn.commit();
			}

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

		return result;
	}
	
	
	public ArrayList<Review_COMM_Dto> getComments(int seq) throws ClassNotFoundException {
		
		String sql = " SELECT B.SEQ, B.ID, B.CONTENT, B.DATE "
				   + " FROM REVIEW_COMM A INNER JOIN REVIEW B "
				   + " ON B.REVIEW_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ArrayList<Review_COMM_Dto> list = new ArrayList<Review_COMM_Dto>();
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 selectComments success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 selectComments success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				Review_COMM_Dto dto = new Review_COMM_Dto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setId(rs.getString("ID"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setDate(rs.getString("DATE"));
				list.add(dto);
			}
			System.out.println(list.toString());
			System.out.println("3/6 selectComments success");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	

}
