package member;

import java.io.Serializable;

public class BUSI_Amenity_Dto implements Serializable {
	private String parking;
	private String conv;
	private String bank;
	private String drug;
	private String bmw;
	public BUSI_Amenity_Dto() {
	}
	public BUSI_Amenity_Dto(String parking, String conv, String bank, String drug, String bmw) {
		super();
		this.parking = parking;
		this.conv = conv;
		this.bank = bank;
		this.drug = drug;
		this.bmw = bmw;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getConv() {
		return conv;
	}
	public void setConv(String conv) {
		this.conv = conv;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public String getBmw() {
		return bmw;
	}
	public void setBmw(String bmw) {
		this.bmw = bmw;
	}
	@Override
	public String toString() {
		return "BUSI_Amenity_Dto [parking=" + parking + ", conv=" + conv + ", bank=" + bank + ", drug=" + drug
				+ ", bmw=" + bmw + "]";
	}
	
}
