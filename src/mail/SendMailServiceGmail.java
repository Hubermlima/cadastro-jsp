package mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import interfaces.SendMailService;

public class SendMailServiceGmail implements SendMailService {

	String from;
	String password;
	String to;
	String subject;
	String body;

	public SendMailServiceGmail(String from, String password, String to, String subject, String body) {
		super();
		this.from = from;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	@Override
	public void send() {
		
		 Properties props = new Properties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	       // props.put("mail.debug", "true");
	        props.put("mail.smtp.port", "465"); // Porta
			props.put("mail.smtp.socketFactory.port", "465"); // Especifica a porta a ser conectada pelo socket
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
	        //classe anonima que realiza a autenticacao
	        //do usuario no servidor de email.
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        };

	        // Cria a sessao passando as propriedades e a autenticacao
	        Session session = Session.getDefaultInstance(props, auth);
	        // Gera um log no console referente ao processo de envio
	        //session.setDebug(true);
	        
	     // compose message
			try {
				
				Address[] toUser = InternetAddress.parse(to); 
				Message mensagem = new MimeMessage(session);
				// Quem está enviando, ou seja, o remetente
				mensagem.setFrom(new InternetAddress(from)); 
				// Quem está recebendo, neste caso, particular, para ele mesmo
				mensagem.setRecipients(Message.RecipientType.TO, toUser); 
				// Assunto do email
				mensagem.setSubject(subject);
				mensagem.setText(body);

				Transport.send(mensagem);

			} catch (MessagingException e) {
				e.printStackTrace();
			}
			

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
