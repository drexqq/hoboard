package Reserve;

import java.util.List;

public class Reserve_Dao {
	
	private static Reserve_Dao dao = new Reserve_Dao();

private Reserve_Dao() {
		
	}
	
	public static Reserve_Dao getInstance() {
		return dao;
	}
	

	//TODO Paging NERMU URYUWAR
	public List<Review_Dto> getReview_PagingList(String choiceloc, String choiceamt , String searchWord ) {
					
		String sql = " SELECT RESERVE_SEQ, BUSI_ID, INDVD_ID, "
				+ " CATE, RESERVE_TIME, CONT, STATUS, RESERVE_DATE "
				+ " FROM ";
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY REVIEW_SEQ DESC) AS RNUM, "
			+  " RESERVE_SEQ, BUSI_ID, INDVD_ID, CATE, RESERVE_TIME, CONT, STA TUS, RESERVE_DATE "
			+  " FROM RESERVE ";
		
		String sqlWord = "";
		
		//int loc = Integer.parseInt(choiceloc);
		int amt = Integer.parseInt(choiceamt);
		
		
		for(int i = 0; i<18; i++) {
		
		}
		
		if(choice.equals("id")) {
			sqlWord = " WHERE INDVD_ID='" + searchWord.trim() + "'";
		}else if (choice.equals("busi_name")) {
			sqlWord = " WHERE BUSI_CATE LIKE '%" + searchWord.trim() + "%' ";
		}else if (choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if (choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		}else if (choice.equals("score")) {
			sqlWord = " WHERE SCORE='" + searchWord.trim() + "'";
		}else if (choice.equals("sel")) {
			sqlWord = "WHERE DEL= 0 ";
		}
		sql = sql + sqlWord;
		
		sql += " ORDER BY REVIEW_SEQ DESC) ";
		sql += " WHERE RNUM >= ? AND RNUM <= ? ";
	
	
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Review_Dto> list = new ArrayList<Review_Dto>();
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			rs = psmt.executeQuery();
			
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
												rs.getString(i++),
												rs.getString(i++),
												rs.getInt(i++));				
				list.add(dto);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		System.out.println("get list done");
		return list;
	}
}
