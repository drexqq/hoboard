package Ask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class Ask_Comm_Dao {

	private static Ask_Comm_Dao dao = new Ask_Comm_Dao();
	
	private Ask_Comm_Dao() {
	}

	public static Ask_Comm_Dao getInstance() {
		return dao;
	}
	
	// TODO insert comment
	public boolean comm_write(Ask_Comm_Dto dto2) {

		String sql = " INSERT INTO ASK_COMM " 
		+ " VALUES(?, C_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, 0, 0) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 comm_write success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto2.getB_seq());
			psmt.setString(2, dto2.getId());
			psmt.setString(3, dto2.getContent());

			System.out.println("2/6 comm_write success");

			count = psmt.executeUpdate();

			System.out.println("3/6 comm_write success");

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
	
	
	public List<Ask_Comm_Dto> getComm(int c_seq) {
		
		String sql = " SELECT * "
				   + " FROM ASK A INNER JOIN ASK_COMM B "
				   + " ON A.C_SEQ = ? "
				   + " ORDER BY C_SEQ DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ArrayList<Ask_Comm_Dto> list = new ArrayList<Ask_Comm_Dto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 selectComments success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, c_seq);
			System.out.println("2/6 selectComments success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int i = 1;
				Ask_Comm_Dto dto = new Ask_Comm_Dto(rs.getInt(i++), 
														rs.getInt(i++),
														rs.getString(i++), 
														rs.getString(i++), 
														rs.getString(i++),
														rs.getInt(i++),
														rs.getInt(i++),
														rs.getInt(i++));				
				list.add(dto);
				
			}
			System.out.println(list.toString());
			System.out.println("3/6 selectComments success");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}	
	
	public boolean comm_delete(int c_seq) {
		
		String sql = " DELETE FROM ASK_COMM "
					+ " WHERE C_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Ask_Comm_Dto dto2 = null;
		
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
	
	public Ask_Comm_Dto getCseq( int c_seq ) {
		
		String sql = " SELECT * FROM "
				+ " ASK_COMM "
				+ " WHERE C_SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Ask_Comm_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCseq success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, c_seq);
			System.out.println("2/6 getCseq success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getCseq success");
			
			while(rs.next()){
				dto = new Ask_Comm_Dto();
				dto.setB_seq(rs.getInt("b_seq"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("wdate"));
				dto.setC_seq(rs.getInt("c_seq"));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);
		}		
		
		return dto;	
	}	
	
	public boolean comm_update(int c_seq, String content) {
		String sql = " UPDATE ASK_COMM SET "
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
