package news;

public class news_comm_dto {

	private int b_seq;
	private String id;
	private String content;
	private String wdate;
	private int c_seq;
	
	public news_comm_dto() {
		
	}

	public news_comm_dto(int b_seq, String id, String content, String wdate, int c_seq) {
		super();
		this.b_seq = b_seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.c_seq = c_seq;
	}

	
	
	
	public news_comm_dto(String id, String content, int b_seq) {
		super();
		this.id = id;
		this.content = content;
		this.b_seq = b_seq;
	}

	public int getB_seq() {
		return b_seq;
	}

	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
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

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	@Override
	public String toString() {
		return "news_comm_dto [b_seq=" + b_seq + ", id=" + id + ", content=" + content + ", wdate=" + wdate + ", c_seq="
				+ c_seq + "]";
	}


	
	
	
}
