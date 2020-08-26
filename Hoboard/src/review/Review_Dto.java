package review;

import java.io.Serializable;

public class Review_Dto implements Serializable {
	
	private int review_seq;	 // sequence
	
	private String busi_id;	 
	private String indvd_id; 
	
	private String title;	 
	private String content;  
	
	private int viewcount;	 
	private int score;		  //starpoint ★★★★★
	
	private String wdate;	  //date
	
	private String filename;  //fileupload
	private String busi_cate; //[hospital-obj]
	
	private int del;

	
	public Review_Dto() {
	}
	
	public Review_Dto(int score) {
		this.score = score;
	}

	public Review_Dto(int review_seq, String busi_id, String indvd_id, String title, String content, int viewcount,
			int score, String wdate, String filename, String busi_cate, int del) {
		super();
		this.review_seq = review_seq;
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.score = score;
		this.wdate = wdate;
		this.filename = filename;
		this.busi_cate = busi_cate;
		this.del = del;
	}

	public Review_Dto(String busi_id, String indvd_id, String title, String content, int score, String filename, String busi_cate) {
		super();
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.title = title;
		this.content = content;
		this.score = score;
		this.filename = filename;
		this.busi_cate = busi_cate;
	}

	public int getReview_seq() {
		return review_seq;
	}


	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getViewcount() {
		return viewcount;
	}


	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getBusi_cate() {
		return busi_cate;
	}


	public void setBusi_cate(String busi_cate) {
		this.busi_cate = busi_cate;
	}
	
	


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	@Override
	public String toString() {
		return "Review_Dto [review_seq=" + review_seq + ", busi_id=" + busi_id + ", indvd_id=" + indvd_id + ", title="
				+ title + ", content=" + content + ", viewcount=" + viewcount + ", score=" + score + ", wdate=" + wdate
				+ ", filename=" + filename + ", busi_cate=" + busi_cate + ", del=" + del + "]";
	}


	

	
	
	
	


	









	


	


	
	
	
	
	
	
	
	
	
	

}
