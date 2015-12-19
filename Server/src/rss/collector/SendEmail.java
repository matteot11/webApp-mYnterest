package rss.collector;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void send(String to, String myTopics, TopicArray newTopics) {

		
		TopicArray toSend = new TopicArray();
		
		for (String t : newTopics)	{
			if(myTopics.contains(t))	{
				toSend.add(t);
			}
		}
		
		
		if (!toSend.isEmpty())	{
			final String username = "mynterestwebapp@gmail.com";
			final String password = "matteofabiomynterest";
	
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
	
			try {
	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("mynterestwebapp@gmail.com"));  	//mittente
				message.setRecipients(Message.RecipientType.TO,                    
					InternetAddress.parse(to));				//destinatario
				message.setSubject("Ci sono nuove notizie per te!");   //oggetto mail
				message.setText("Sono state aggiunte le ultime notizie nei topic:\n"
								+ toSend.toString());
	
				Transport.send(message);
	
				System.out.println("Done");
	
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}