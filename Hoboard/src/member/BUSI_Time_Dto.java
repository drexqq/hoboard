package member;


import java.io.Serializable;
import java.util.Arrays;

public class BUSI_Time_Dto extends Member_Dto implements Serializable {

	public BUSI_Time_Dto() {
		
	}
	
	private String id;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;
	private String lunch;
	private int holiday;
	private int night;
	private int emer;
	
	
	public BUSI_Time_Dto(String id, String mon, String tue, String wed, String thu, String fri, String sat, String sun,
			String lunch, int holiday, int night, int emer) {
		super();
		this.id = id;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
		this.lunch = lunch;
		this.holiday = holiday;
		this.night = night;
		this.emer = emer;
	}

	
	

	public BUSI_Time_Dto(String id, String mon, String tue, String wed, String thu, String fri, String sat, String sun,
			String lunch) {
		super();
		this.id = id;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
		this.lunch = lunch;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMon() {
		return mon;
	}


	public void setMon(String mon) {
		this.mon = mon;
	}


	public String getTue() {
		return tue;
	}


	public void setTue(String tue) {
		this.tue = tue;
	}


	public String getWed() {
		return wed;
	}


	public void setWed(String wed) {
		this.wed = wed;
	}


	public String getThu() {
		return thu;
	}


	public void setThu(String thu) {
		this.thu = thu;
	}


	public String getFri() {
		return fri;
	}


	public void setFri(String fri) {
		this.fri = fri;
	}


	public String getSat() {
		return sat;
	}


	public void setSat(String sat) {
		this.sat = sat;
	}


	public String getSun() {
		return sun;
	}


	public void setSun(String sun) {
		this.sun = sun;
	}


	public String getLunch() {
		return lunch;
	}


	public void setLunch(String lunch) {
		this.lunch = lunch;
	}


	public int getHoliday() {
		return holiday;
	}


	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}


	public int getNight() {
		return night;
	}


	public void setNight(int night) {
		this.night = night;
	}


	public int getEmer() {
		return emer;
	}


	public void setEmer(int emer) {
		this.emer = emer;
	}


	@Override
	public String toString() {
		return "BUSI_Time_Dto [id=" + id + ", mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thu=" + thu + ", fri="
				+ fri + ", sat=" + sat + ", sun=" + sun + ", lunch=" + lunch + ", holiday=" + holiday + ", night="
				+ night + ", emer=" + emer + "]";
	}
	        
	
	
	
	
	
}