package member;

public class BUSI_Member_Dao {
	
private static BUSI_Member_Dao dao = new BUSI_Member_Dao();
	

	private BUSI_Member_Dao() {
	
	}
	
	public static BUSI_Member_Dao getInstance() {
		return dao;
	}
	

	
}
