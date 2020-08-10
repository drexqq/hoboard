package review;

import java.io.Serializable;

public class Review_Dto implements Serializable {
	
	private int review_seq;	 // sequence
	
	private String busi_id;	 // 병원측 조회 아이디
	private String indvd_id; // 작성자
	
	private String title;	 // 타이틀
	private String content;  // 내용
	
	private int viewcount;	 // 조회수
	private int score;		 // 평점
	
	private String w_date;	 // 작성일
	
	private int ref;		 // 그룹번호		 
	private int step;		 // 행(row)번호
	private int depth;		 // ㄴ> 깊이(depth)
	
	
	public Review_Dto(int review_seq, String busi_id, String indvd_id, String title, String content, int viewcount,
			int score, String w_date, int ref, int step, int depth) {
		super();
		this.review_seq = review_seq;
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.score = score;
		this.w_date = w_date;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
	}


	


	public Review_Dto(String busi_id, String indvd_id, String title, String content, int score) {
		super();
		this.busi_id = busi_id;
		this.indvd_id = indvd_id;
		this.title = title;
		this.content = content;
		this.score = score;
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


	public String getW_date() {
		return w_date;
	}


	public void setW_date(String w_date) {
		this.w_date = w_date;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	@Override
	public String toString() {
		return "Review_Dto [review_seq=" + review_seq + ", busi_id=" + busi_id + ", indvd_id=" + indvd_id + ", title="
				+ title + ", content=" + content + ", viewcount=" + viewcount + ", score=" + score + ", w_date="
				+ w_date + ", ref=" + ref + ", step=" + step + ", depth=" + depth + "]";
	}
	
	
	
	
	
	
	
	
	

}
