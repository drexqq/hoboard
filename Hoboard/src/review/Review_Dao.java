package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class Review_Dao {
	
private static Review_Dao dao = new Review_Dao();
	
	private Review_Dao() {
		
	}
	
	public static Review_Dao getInstance() {
		return dao;
	}

	// TODO all list SELECT
	public List<Review_Dto> getReviewList() {
		
		String sql = " SELECT REVIEW_SEQ, BUSI_ID, INDVD_ID, "
			       + " TITLE, CONTENT, VIEWCOUNT, SCORE, W_DATE, REF, STEP, DEPTH "
			       + " FROM REVIEW "
			       + " ORDER BY REF DESC, STEP ASC ";
		
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
			
			while(rs.next()) {
				int i = 1;
				Review_Dto dto = new Review_Dto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++), 
										rs.getInt(i++),
										rs.getInt(i++), 
										rs.getInt(i++));				
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
		
		String sql = "INSERT INTO REVIEW "
				   + " ( REVIEW_SEQ, BUSI_ID, INDVD_ID, "
				   + " TITLE, CONTENT, VIEWCOUNT, SCORE, W_DATE, REF, STEP, DEPTH) "
				   + " VALUES( SEQ_REVIEW.NEXTVAL, ? , ? , "
				   + " ?, ?, 0, ?, SYSDATE, (SELECT NVL(MAX(REF), 0)+1 FROM REVIEW) , 0 , 0 ";
		
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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
}
