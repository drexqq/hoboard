package Ask;

public class Ask_Comm_Dto {

	private int b_seq;
	private int c_seq;
	private String id;
	private String content;
	private String wdate;
	private int ref;
	private int step;
	private int depth;

	public Ask_Comm_Dto() {
		
	}

	public Ask_Comm_Dto(int b_seq, int c_seq, String id, String content, String wdate, int ref, int step, int depth) {
		super();
		this.b_seq = b_seq;
		this.c_seq = c_seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
	}

	public int getB_seq() {
		return b_seq;
	}

	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
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
		return "Ask_Comm_Dto [b_seq=" + b_seq + ", c_seq=" + c_seq + ", id=" + id + ", content=" + content + ", wdate="
				+ wdate + ", ref=" + ref + ", step=" + step + ", depth=" + depth + "]";
	}
	
	
	
	
	
}
