package edu.nuist.beans;



public class Email {
	private String receiver="";
	private String title="";
	private String content="";
	
	public Email(String receiver, String title, String content) {
		super();
		this.receiver = receiver;
		this.title = title;
		this.content = content;
	}
	public Email() {
		super();
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	
}
