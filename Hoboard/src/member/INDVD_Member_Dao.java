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
	
	public boolean addINDVD_Member(String id) {
		System.out.println("개인 회원가입");
		return true;
	}
	
}
