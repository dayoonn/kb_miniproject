package mp.vo;

import java.util.Date;

public class BoardDto {
	private int no;
	private String title, writer;
	private Date regdate;
	private String content;

	public BoardDto() {
	}

	public BoardDto(int no, String title, String writer, Date regdate, String content) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "no=" + no + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate + ", content="
				+ content ;
	}

	
}
