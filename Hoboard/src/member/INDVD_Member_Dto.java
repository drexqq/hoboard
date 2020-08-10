package member;


import java.io.Serializable;

public class INDVD_Member_Dto extends Member_Dto implements Serializable {

	public INDVD_Member_Dto() {
		
	}
	
	private String id;

	public INDVD_Member_Dto(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "INDVD_Member_Dto [id=" + id + "]";
	}
	
	
}
