package Reserve;

import java.io.Serializable;

public class Reserve_Dto implements Serializable {
	
	int reserve_seq;
	String busi_id;
	String indvd_id;
	String cate;
	String reserve_time;
	String cont;
	int status;
	String reserve_date;
	
	public Reserve_Dto() {
	
	}

	public Reserve_Dto(int reserve_seq, String busi_id, String indvd_id, String cate, String reserve_time, String cont,
			int status, String reserve_date) {
		super();
		this.reserve_seq = reserve_seq;
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.cate = cate;
		this.reserve_time = reserve_time;
		this.cont = cont;
		this.status = status;
		this.reserve_date = reserve_date;
	}
	
	public Reserve_Dto(int reserve_seq, String busi_id, String indvd_id, String cate, String cont, int status, String reserve_time,
			 String reserve_date) {
		super();
		this.reserve_seq = reserve_seq;
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.cate = cate;
		this.reserve_time = reserve_time;
		this.cont = cont;
		this.status = status;
		this.reserve_date = reserve_date;
	}

	public int getReserve_seq() {
		return reserve_seq;
	}

	public void setReserve_seq(int reserve_seq) {
		this.reserve_seq = reserve_seq;
	}

	public String getBusi_id() {
		return busi_id;
	}

	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}

	public String getIndvd_id() {
		return indvd_id;
	}

	public void setIndvd_id(String indvd_id) {
		this.indvd_id = indvd_id;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	@Override
	public String toString() {
		return "reserve_seq=" + reserve_seq + ", busi_id=" + busi_id + ", indvd_id=" + indvd_id + ", cate="
				+ cate + ", reserve_time=" + reserve_time + ", cont=" + cont + ", status=" + status + ", reserve_date="
				+ reserve_date;
	}
}