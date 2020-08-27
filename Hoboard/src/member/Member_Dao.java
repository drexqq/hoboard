package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class Member_Dao {

	private static Member_Dao dao = new Member_Dao();

	public static Member_Dao getInstance() {
		return dao;
	}

	public String login(String id, String pw) {
		String sql = " SELECT * " + "	FROM MEMBER " + " WHERE ID=? AND PW =? ";
		String name = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
				name = rs.getString("NAME");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return name;
	}

	// INSERT INTO MEMBER TABLE
	public boolean addMember(Member_Dto dto) {
		String sql = " INSERT INTO MEMBER " + " (AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

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

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// UPDATE USER MEMBER TABLE
	public boolean updateMember(Member_Dto dto) {
		String query = " UPDATE MEMBER" + " SET" + " PW = ?, " + " TEL = ?, " + " ADDRESS = ?," + " D_ADDRESS = ? "
				+ " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getPw());
			psmt.setString(2, dto.getTel());
			psmt.setString(3, dto.getAddress());
			psmt.setString(4, dto.getD_Address());
			psmt.setString(5, dto.getId());

			count = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// GET ID MEMBER
	public boolean chkId(String id) {
		String query = " SELECT ID" + " FROM MEMBER" + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next())
				exist = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return exist;
	}

	// GET EMAIL MEMBER
	public boolean chkEmail(String email) {
		String query = " SELECT EMAIL" + " FROM MEMBER" + " WHERE EMAIL = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			rs = psmt.executeQuery();

			if (rs.next())
				exist = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return exist;
	}

	// GET BUSI_CATE COLUMN
	public String[] getBusiCateList() {
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
			while (rs.next())
				cate[i++] = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cate;
	}

	// GET BUSI CATE
	public String[] getBusiCate(String id) {
		String query = " SELECT * FROM BUSI_CATE WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String cate[] = new String[16];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			int j = 2;
			while (rs.next()) {
				for (int i = 0; i < cate.length; i++)
					cate[i] = rs.getString(j++);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cate;
	}

	// GET BUSI_TIME TABLE COLUMN
	public String[] getBusiTimeList() {
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
			while (rs.next())
				time[i++] = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	// GET BUSI TIME
	public String[] getBusiTime(String id) {
		System.out.println("get Busi Time");
		String query = " SELECT * FROM BUSI_TIME WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String time[] = new String[11];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			int j = 2;
			while (rs.next()) {
				for (int i = 0; i < time.length; i++)
					time[i] = rs.getString(j++);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	// GET BUSI_AMENITY TABLE COLUMN
	public String[] getAmenityList() {
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
			while (rs.next()) {
				amenity[i++] = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return amenity;
	}

	// GET BUSI AMENITY
	public String[] getBusiAmenity(String id) {
		String query = " SELECT * FROM BUSI_AMENITY WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String amenity[] = new String[5];
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			int j = 2;
			while (rs.next()) {
				for (int i = 0; i < amenity.length; i++)
					amenity[i] = rs.getString(j++);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return amenity;
	}

	// GET USER INFO
	public Member_Dto getUser(String id) {
		String query = " SELECT " + " AUTH , ID, NAME, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS " + " FROM MEMBER "
				+ " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		Member_Dto dto = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				int i = 1;
				dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	// GET MEMBER WHERE AUTH = 2
	public List<Member_Dto> getBusiMember() {
		String query = " SELECT NAME, TEL, ADDRESS, D_ADDRESS FROM MEMBER WHERE AUTH = 2 ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Member_Dto dto = new Member_Dto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("get busimember from member table done");
		return list;
	}
	
	public List<Member_Dto> getBusiMember_admin() {
		
		String query = " SELECT AUTH, ID, NAME, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS FROM MEMBER WHERE AUTH = 2 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Member_Dto dto = new Member_Dto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("get busimember from member table done");
		return list;
	}
	
	public List<Member_Dto> getPMember_admin() {
		
		String query = " SELECT AUTH, ID, NAME, TEL, EMAIL FROM MEMBER WHERE AUTH = 1 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Member_Dto dto = new Member_Dto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("get busimember from member table done");
		return list;
	}
	
	//회원 탈퇴 AUTH 3(개인)으로 관리
	public Member_Dto DelIMember(String id) {
		
		String query = " UPDATE MEMBER SET AUTH = 3 WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Member_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
		System.out.println("auth 3 대기중 1/3");
			rs = psmt.executeQuery();
		System.out.println("auth 3 대기중 2/3");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		System.out.println("auth 3 success");
		
		return dto;

	}
	//회원 탈퇴 AUTH 4(병원)으로 관리
	public Member_Dto DelBMember(String id) {
		
		String query = " UPDATE MEMBER SET AUTH = 4 WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Member_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
		System.out.println("auth 4 대기중 1/3");
			rs = psmt.executeQuery();
		System.out.println("auth 4 대기중 2/3");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		System.out.println("auth 4 success");
		
		return dto;

	}
	
}
