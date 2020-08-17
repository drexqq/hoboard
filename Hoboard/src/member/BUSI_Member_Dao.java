package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import oracle.jdbc.proxy.annotation.Pre;

public class BUSI_Member_Dao{
	public static String[] cate = {"내과", "마취통증학과", "산부인과", "소아청소년과", "신경과", "신경외과", "심장내과", "영상의학과", "외과", "응급의학과", "정형외과", "재활의학과", "흉부심장혈관과", "피부비뇨기과", "치과", "안과"};
	public static String[] time = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일", "점심시간", "공휴일", "야간진료", "응급실"};
	public static String[] amenity = {"주차장", "편의점", "ATM,은행", "약국", "대중 교통"};
	private static BUSI_Member_Dao dao = new BUSI_Member_Dao();
	
	private BUSI_Member_Dao() {		
	}
	public static BUSI_Member_Dao getInstance() {
		return dao;
	}
	
	public boolean addBUSI_Member(BUSI_Member_Dto b_dto, String id) {
		System.out.println("BUSI_MEMBER TABLE INSERT");
		String query = " INSERT INTO BUSI_MEMBER "
					+ " VALUES "
					+ " ('"+id+"', ?, ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, b_dto.getHomepage());
			psmt.setString(2, b_dto.getLogo());
			count = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		System.out.println("BUSI_MEMBER INSERT DONE");
		System.out.println(count);
		return count > 0 ? true : false;
	}
	
	public boolean addBUSI_Extra(String id, String time[], int extra[], int cate[], int amenity[]) {
		System.out.println("BUSI_... TABLES INSERT");
		String time_query = " INSERT INTO BUSI_TIME "
						+ " VALUES "
						+ " ('"+id+"',"
						+ " ?, ?, ?, ?, ?, ?, ?, ?, "
						+ " ?, ?, ? ) ";
		String cate_query = " INSERT INTO BUSI_CATE "
						+ " VALUES "
						+ " ('"+id+"', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		String amenity_query = " INSERT INTO BUSI_AMENITY "
						+ " VALUES "
						+ " ('"+id+"', ?, ?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			// BUSI_TIME TABLE INSERT
			psmt = conn.prepareStatement(time_query);
			int j = 0;
			for (int i = 0; i < time.length; i++) psmt.setString((i+1), time[i]);
			for (int i = 8; i < time.length + extra.length; i++) psmt.setInt((i+1), extra[j++]);
			psmt.executeUpdate();
			psmt.clearParameters();
			
			// BUSI_CATE TABLE INSERT
			psmt = conn.prepareStatement(cate_query);
			for (int i = 0; i < cate.length; i++) psmt.setInt((i+1), cate[i]);
			psmt.executeUpdate();
			psmt.clearParameters();
			
			// BUSI_AMENITY TABLE INSERT
			psmt = conn.prepareStatement(amenity_query);
			for (int i = 0; i < amenity.length; i++) psmt.setInt((i+1), amenity[i]);
			count = psmt.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			DBClose.close(psmt, conn, null);
			System.out.println("BUSI_... TABLES INSERT DONE");
		}
		return count > 0 ? true : false;
	}
	
	
	
}
