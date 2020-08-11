package member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DBClose;
import db.DBConnection;

public class INDVD_Member_Dao {

	private static INDVD_Member_Dao dao = new INDVD_Member_Dao();
	
	private INDVD_Member_Dao() {		
	}
	public static INDVD_Member_Dao getInstance() {
		return dao;
	}
	
	public boolean addINDVD_Member(String id) {
		System.out.println("INDVD_MEMBER TABLE INSERT");
		String query = " INSERT INTO INDVD_MEMBER "
					+ " VALUES "
					+ " ('"+id+"') ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			count = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		System.out.println("INDVD_MEMBER INSERT DONE");
		return count > 0 ? true : false;
	}
	
}
