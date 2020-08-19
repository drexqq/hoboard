package Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import member.Member_Dto;
import review.Review_Dto;



public class Reserve_Dao {

	private static Reserve_Dao dao = new Reserve_Dao();

	private Reserve_Dao() {

	}

	public static Reserve_Dao getInstance() {
		return dao;
	}
	
	//TODO search
	public List<Member_Dto> getReserve_list(String loc, String amt, String searchWord){
		String sql = " SELECT NAME, TEL , ADDRESS " 
				   + " FROM MEMBER ";
				
		//String sql += " "	
		
		//WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE INTERNAL = 1)
		//AND ADDRESS LIKE '%서울%' AND NAME LIKE '%w%';
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		String sqlamt = "";
		String sqlloc = "";
		
		
		if(amt.equals("내과")) {
			sqlamt = " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE INTERNAL = 1) ";
		} 
		
		if(loc.equals("서울")) {
			sqlloc = " AND ADDRESS LIKE '%서울%' ";
		} 
		
		String sqlword = " AND NAME LIKE '%" + searchWord.trim() + "%'";
		
		sql = sql + sqlamt + sqlloc + sqlword;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve_list success");
			
			psmt = conn.prepareStatement(sql);
			
			System.out.println("2/6 getInternal_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getInternal_list success");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}

	public List<Member_Dto> getInternal_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE INTERNAL = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getInternal_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getInternal_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getInternal_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getanpn_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE ANPN = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getanpn_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getanpn_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getanpn_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getmtrnt_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE MTRNT = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getmtrnt_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getmtrnt_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getmtrnt_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getpdtrc_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE PDTRC = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getpdtrc_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getpdtrc_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getpdtrc_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getnrlgy_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE NRLGY = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getnrlgy_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getnrlgy_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getnrlgy_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getnrsrg_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE NRSRG = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getnrsrg_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getnrsrg_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getnrsrg_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getcrdlg_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE CRDLG = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getcrdlg_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getcrdlg_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getcrdlg_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getxray_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE XRAY = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getxray_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getxray_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getxray_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getgs_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE GS = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getgs_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getgs_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getgs_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getdprtm_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE DPRTM = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getdprtm_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getdprtm_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getdprtm_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getos_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE OS = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getos_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getos_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getos_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<Member_Dto> getrhblt_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE RHBLT = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getrhblt_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getrhblt_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getrhblt_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getthrcc_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE THRCC = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getthrcc_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getthrcc_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getthrcc_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getskin_uro_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE SKIN_URO = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getskin_uro_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getskin_uro_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getskin_uro_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getdent_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE DENT = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getdent_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getdent_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getdent_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public List<Member_Dto> getophth_list(int seq) {
		String sql = "SELECT AUTH, NAME, ID, PW, TEL, EMAIL, POST_NUM, ADDRESS, D_ADDRESS "
				+ " FROM MEMBER "
				+ " WHERE ID IN (SELECT ID FROM BUSI_CATE WHERE OPHTH = ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Member_Dto> list = new ArrayList<Member_Dto>();
		
		try {

			conn = DBConnection.getConnection();
			System.out.println("1/6 getophth_list success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getophth_list success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getophth_list success");
			
	
			while (rs.next()) {
				
				int i = 1;
				Member_Dto dto = new Member_Dto(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
				rs.getString(i++), rs.getString(i++), rs.getString(i++) , rs.getString(i++), rs.getString(i++));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	

}