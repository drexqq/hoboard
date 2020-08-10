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
	
	public boolean addINDVD_Member(INDVD_Member_Dto dto) {
		// 회원가입의 데이터 -> DB
		String sql = " INSERT INTO INDVD_MEMBER "
				+ "	( ID ) " // LOGO 포함
				+ " VALUES(?) "; // LOGO 포함
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addMember success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember success");
			
			psmt.setString(1, dto.getId());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 addMember success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("addMember fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}
	
}
