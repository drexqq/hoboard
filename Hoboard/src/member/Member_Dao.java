package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;

public class Member_Dao {
	
	private static Member_Dao dao = new Member_Dao();
	
	public static Member_Dao getInstance() {
		return dao;
	}
	
	
	public String login(String id, String pw) {
		
		String sql = " SELECT * "
					+ "	FROM MEMBER "
					+ " WHERE ID=? AND PW =? ";
		
		String name = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 login success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			System.out.println("2/6 login success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 login success");
			
			if(rs.next()) {

				name = rs.getString("name");	
			}
			System.out.println("4/6 login success");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return name;
	}
	
	public boolean addMember(Member_Dto dto) {
		// 회원가입의 데이터 -> DB
		String sql = " INSERT INTO MEMBER "
				+ "	(AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS) "
				+ " VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addMember success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember success");
			
			//psmt.setInt(1, dto.getAuth());
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getPw());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getPost_Num());
			psmt.setString(7, dto.getAddress());
			psmt.setString(8, dto.getD_Address());

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
