package news;

import java.io.Serializable;

public class News_Dto implements Serializable {
	
	private int news_seq;
	private String id;
	private String title;
	private String content;
	private String date;
	private int viewcount;
	private String news_file;
	
	public News_Dto() {
	}
	
	
	
	public News_Dto(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public News_Dto(int news_seq, String title, String content) {
		this.news_seq = news_seq;
		this.title = title;
		this.content = content;
	}

	public News_Dto(int news_seq, String id, String title, String content, String date, int viewcount, String news_file) {
		this.news_seq = news_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.viewcount = viewcount;
		this.news_file = news_file;
	}
	 
	public News_Dto(int news_seq, String id, String title, String date, int viewcount) {
			this.news_seq = news_seq;
			this.id = id;
			this.title = title;
			this.date = date;
			this.viewcount = viewcount;
		}
	 
	public News_Dto(String id, String title, String content, String date, int viewcount) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.viewcount = viewcount;
	 }
	 
	 

	public News_Dto(int news_seq, String id, String title, String content, int viewcount, String date) {
		this.news_seq = news_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.viewcount = viewcount;
	 }
	
	public News_Dto(int news_seq, String title, String content,  int viewcount, String date) {
		this.news_seq = news_seq;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.date = date;
	}
	
	public News_Dto(String id, String title, String content, String news_file) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.news_file = news_file;
	}

	public int getNews_seq() {
		return news_seq;
	}

	public void setNews_seq(int news_seq) {
		this.news_seq = news_seq;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public String getNews_file() {
		return news_file;
	}

	public void setFile(String news_file) {
		this.news_file = news_file;
	}


	@Override
	public String toString() {
		return "News_Dto [news_seq=" + news_seq + ", id=" + id + ", title=" + title + ", content=" + content + ", date="
				+ date + ", viewcount=" + viewcount + ", news_file=" + news_file + "]";
	}
	
	
	
}

