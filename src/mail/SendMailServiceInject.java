package mail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import interfaces.SendMailService;

public class SendMailServiceInject {

	SendMailService sendMailService;
	String type;
	String from;
	String password;
	String to;
	String subject;
	String body;

	public SendMailServiceInject(String type, String from, String password, String to, String subject, String body) {
		this.type = type;
		this.from = from;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	public void sendMail() {
		if (type == "gmail") {

			try {
				Constructor<?> c = Class.forName("mail.SendMailServiceGmail").getConstructor(String.class, String.class, String.class, String.class, String.class);
				sendMailService = (SendMailService) c.newInstance(from, password, to, subject, body);
			} catch (IllegalArgumentException | InvocationTargetException | InstantiationException
					| IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		sendMailService.send();

	}

	public SendMailService getSendMailService() {
		return sendMailService;
	}

	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}

}
