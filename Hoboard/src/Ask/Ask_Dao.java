package Ask;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBClose;
import db.DBConnection;

public class Ask_Dao {
	private static Ask_Dao dao = new Ask_Dao();
	
	private Ask_Dao() {
	}
	
	public static Ask_Dao getInstance() {
		return dao;
	}
	
	public List<Ask_Dto> getAskList(String id) {
		String query = " SELECT ID, TITLE, CONTENT, COMM, WDATE FROM ASK_TABLE "
					+ " WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Ask_Dto> list = new ArrayList<Ask_Dto>();
		
		try {
			conn = DBConnection.getConnection();
			psmt.setString(1, id);
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Ask_Dto dto = new Ask_Dto(
							rs.getString(1),	// id
							rs.getString(2),	// title
							rs.getString(3),	// content
							rs.getString(4),	// comm
							rs.getString(5));	// wdate
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);		
	}
	
	
}
