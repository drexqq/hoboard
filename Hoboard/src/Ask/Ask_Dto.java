package Ask;

import java.io.Serializable;

public class Ask_Dto implements Serializable {
	private String id;
	private String title;
	private String content;
	private String comm;
	private String wdate;
	public Ask_Dto() {
	}
	public Ask_Dto(String id, String title, String content, String comm, String wdate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.comm = comm;
		this.wdate = wdate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "Ask_Dto [id=" + id + ", title=" + title + ", content=" + content + ", comm=" + comm + ", wdate=" + wdate
				+ "]";
	}
	
}
