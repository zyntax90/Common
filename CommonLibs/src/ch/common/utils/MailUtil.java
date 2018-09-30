package ch.common.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private String username;
	private String password;

	public MailUtil(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void sendMail(String smtpHost, Mail mail) throws MessagingException {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", smtpHost);
		properties.setProperty("mail.smtp.port", "587");
	
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent().toString());
		message.setFrom(new InternetAddress(mail.getFrom()));
		for (String recepient : mail.getTo())
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

		Transport transport = session.getTransport("smtps");
		try {
			transport.connect(smtpHost, username, password);
			transport.sendMessage(message, message.getAllRecipients());
		} finally {
			transport.close();
		}
	}
}
