package review;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import Reserve.Reserve_Dto;
import db.DBClose;
import db.DBConnection;
import member.Member_Dto;

public class Review_Dao {
	
private static Review_Dao dao = new Review_Dao();
	
	private Review_Dao() {
		
	}
	
	public static Review_Dao getInstance() {
		return dao;
	}

	// TODO all list SELECT
	public List<Review_Dto> getReviewList() {
		
		String sql = " SELECT REVIEW_SEQ, BUSI_ID, "
			       + " INDVD_ID, TITLE, CONTENT, VIEWCOUNT, SCORE, WDATE, REF, STEP, DEPTH "
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
			System.out.println("2/6 wirteReview success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	//TODO status check
	public String checkStatus(String id , int seq) {
		
		String sql = " SELECT STATUS "
				   + " FROM RESERVE "
				   + " WHERE INDVD_ID = ? AND RESERVE_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String check = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 checkStatus success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, seq);
			System.out.println("2/6 checkStatus success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 checkStatus success");
			
			if(rs.next()) {
				check = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return check;
	}
	
	//TODO ALL RESERVE DATA
	public Reserve_Dto getReserve_Data(String id, int seq){
		
		String sql = " SELECT RESERVE_SEQ, BUSI_ID, INDVD_ID, CATE, "
				   + " RESERVE_TIME, CONT, STATUS, RESERVE_DATE "
				   + " FROM RESERVE "
				   + " WHERE INDVD_ID=? AND RESERVE_SEQ =? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Reserve_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserveData success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, seq);
			System.out.println("2/6 getReserveData success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getReserveData success");
			
			
			while(rs.next()) {
				int i = 1;
				dto = new Reserve_Dto(rs.getInt(i++), 
												rs.getString(i++), 
												rs.getString(i++), 
												rs.getString(i++), 
												rs.getString(i++),
												rs.getString(i++),
												rs.getInt(i++),
												rs.getString(i++));				
			}
			System.out.println("4/6 getReserveData success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}		
		return dto;
	}
	
	//TODO get BUSI_member ID
	public Member_Dto getBusi_Name(String busi , int seq) {
		
		String sql = " SELECT A.NAME "
				   + " FROM MEMBER A INNER JOIN RESERVE B "
				   + " ON A.ID =? AND B.RESERVE_SEQ =? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Member_Dto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserveData success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, busi);
			psmt.setInt(2, seq);
			System.out.println("2/6 getReserveData success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getReserveData success");
			
			
			while(rs.next()) {
				int i = 1;
				dto = new Member_Dto();
				dto.setName(rs.getString(i));
				
				
			}
			System.out.println("4/6 getReserveData success");
			
			/*
			 * for (Member_Dto m : list) { System.out.println(m.toString()); }
			 * System.out.println("5/6 getReserveData success");
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
	}
	
	//TODO get INDVD_member CATEGORY
		public Reserve_Dto getReserve_Cate(String id , int seq) {
			
			String sql = " SELECT CATE "
					   + " FROM RESERVE "
					   + " WHERE INDVD_ID = ? and RESERVE_SEQ = ? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			Reserve_Dto dto = null;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/6 getReserve_Cate success");
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setInt(2, seq);
				System.out.println("2/6 getReserve_Cate success");
				
				rs = psmt.executeQuery();
				System.out.println("3/6 getReserve_Cate success");
				
				
				while(rs.next()) {
					int i = 1;
					dto = new Reserve_Dto();
					dto.setCate(rs.getString(i));
					
					
				}
				System.out.println("4/6 getReserveData success");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBClose.close(psmt, conn, rs);
			}
			
			return dto;
		}
	
		//TODO FILE UPLOAD
		public String processUploadFile(FileItem fileItem, String dir)  {
			
			String filename = fileItem.getName(); 
			System.out.println(filename);

			long sizeInBytes = fileItem.getSize();
			System.out.println(sizeInBytes);

			if (sizeInBytes > 0) { // d:\\tmp\\abc.txt d:/tmp/abc.txt \" \\

				int idx = filename.lastIndexOf("\\");
				if (idx == -1) {
					idx = filename.lastIndexOf("/");
				}

				filename = filename.substring(idx + 1); // -> abc.txt
				File uploadFile = new File(dir, filename);

				try {
					fileItem.write(uploadFile); // 실제 upload 부분
				} catch (Exception e) {
				}
			}
			return filename; // DB return
		}
	
		
	
	
}
