package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			System.out.println("login done");
		}
		
		return name;
	}
	
	// INSERT INTO MEMBER TABLE
	public boolean addMember(Member_Dto dto) {
		System.out.println("MEMBER TABLE INSERT");
		// 회원가입의 데이터 -> DB
		String sql = " INSERT INTO MEMBER "
				+ " (AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addMember success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember success");
			
			psmt.setInt(1, dto.getAuth());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getPw());
			psmt.setString(5, dto.getTel());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getPost_Num());
			psmt.setString(8, dto.getAddress());
			psmt.setString(9, dto.getD_Address());

			count = psmt.executeUpdate();
			System.out.println("3/6 addMember success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("addMember fail");
		} finally {
			DBClose.close(psmt, conn, null);	
			System.out.println("addMember done");
		}
		System.out.println(count);
		return count > 0 ? true : false;
	}
	
	// GET IDS MEMBER
	public boolean chkId(String id) {
		String query = " SELECT ID"
					+ " FROM MEMBER"
					+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) exist = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return exist;
	}
	
	// GET BUSI_CATE COLUMN
	public String[] getBusiCate() {
		String query = " SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'BUSI_CATE' AND COLUMN_NAME != 'ID' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String cate[] = new String[16];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
		
			int i = 0;
			while(rs.next()) {
				cate[i++] = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cate;
	}
	
	// GET BUSI_TIME TABLE COLUMN
	public String[] getBusiTime() {
		String query = " SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'BUSI_TIME' AND COLUMN_NAME != 'ID' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String time[] = new String[11];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
		
			int i = 0;
			while(rs.next()) {
				time[i++] = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
	
	// GET BUSI_AMENITY TABLE COLUMN
	public String[] getAmenity() {
		String query = " SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'BUSI_AMENITY' AND COLUMN_NAME != 'ID' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String amenity[] = new String[5];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
		
			int i = 0;
			while(rs.next()) {
				amenity[i++] = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amenity;
	}
	
}
