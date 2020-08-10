package member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DBClose;
import db.DBConnection;

public class BUSI_Member_Dao{
	
	private static BUSI_Member_Dao dao = new BUSI_Member_Dao();
	
	private BUSI_Member_Dao() {		
	}
	public static BUSI_Member_Dao getInstance() {
		return dao;
	}
	
	public boolean addBUSI_Member(BUSI_Member_Dto bDto) {
		// 회원가입의 데이터 -> DB
		String sql = " INSERT INTO BUSI_MEMBER "
				+ "	( ID, TIME, HOMEPAGE ) " // LOGO 포함해야함!
				+ " VALUES(?, ?, ?) "; // LOGO 포함해야함!
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addMember success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember success");
			
			psmt.setString(1, bDto.getId());
			psmt.setString(2, bDto.getTime());
			psmt.setString(3, bDto.getHomepage()); //LOGO 포함해야함
			
			//System.out.println(bDto.getId());	// 일단 값은 넘어 오고 있음
			
			
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
