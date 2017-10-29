package manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;

import entity.Users;

public class MailHelper {

	private final static String USER_NAME 	= "lior2012.al";
	private final static String PASS 		= "4ras4life";
	private final static String FROM_MAIL 	= "lior2012.al@gmail.com";
	
	public static void sendMail(String to, String subject, String msg) throws MessagingException{
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASS);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM_MAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(msg);

		Transport.send(message);

	}
	
}
