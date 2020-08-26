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
	public BUSI_Member_Dto(String homepage, String logo) {
		this.homepage = homepage;
		this.logo = logo;
	}
	
	public BUSI_Member_Dto(String id, String homepage, String logo) {
		super();
		this.id = id;
		this.homepage = homepage;
		this.logo = logo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "BUSI_Member_Dto [id=" + id + ", homepage=" + homepage + ", logo=" + logo + "]";
	}
	
	
	
}