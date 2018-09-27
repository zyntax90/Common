package ch.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mail {
	private String subject;
	private String from;
	private List<String> to = new ArrayList<String>();
	private Date sendDate;
	private Object content;

	public Mail() {
	}

	public Mail(String from, String to, String subject, Object content) {
		this.from = from;
		this.to.add(to);
		this.subject = subject;
		this.content = content;
	}

	public Mail(String from, List<String> to, String subject, Object content) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void addRecipient(String to) {
		this.to.add(to);
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
