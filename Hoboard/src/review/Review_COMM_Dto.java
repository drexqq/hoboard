package review;

import java.io.Serializable;

public class Review_COMM_Dto implements Serializable {
	
	int seq;
	int board_no;
	String id;
	String content;
	String date;
	
	
	public Review_COMM_Dto() {}


	public Review_COMM_Dto(int seq, int board_no, String id, String content, String date) {
		super();
		this.seq = seq;
		this.board_no = board_no;
		this.id = id;
		this.content = content;
		this.date = date;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	


	public int getBoard_no() {
		return board_no;
	}


	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Review_COMM_Dto [seq=" + seq + ", board_no=" + board_no + ", id=" + id + ", content=" + content
				+ ", date=" + date + "]";
	}
}
