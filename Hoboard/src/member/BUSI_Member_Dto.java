package member;


import java.io.Serializable;
import java.util.Arrays;

public class BUSI_Member_Dto extends Member_Dto implements Serializable {

	public BUSI_Member_Dto() {
		
	}
	
	private String id;
	private String time;
	private String homepage;
	private String logo;
	 // 진료 시간
	public BUSI_Member_Dto(String id, String time, String homepage, String logo) {
		super();
		this.id = id;
		this.time = time;
		this.homepage = homepage;
		this.logo = logo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BUSI_Member_Dto [id=" + id + ", time=" + time + ", homepage=" + homepage + ", logo=" + logo + "]";
	}
	
	
	
}