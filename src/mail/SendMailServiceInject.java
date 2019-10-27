package mail;

import interfaces.SendMailService;

public class SendMailServiceInject {
	
	SendMailService sendMailService;

	public SendMailServiceInject(SendMailService sendMailServiceImpl) {
		this.sendMailService = sendMailServiceImpl;
	}

	
	public SendMailService getSendMailService() {
		return sendMailService;
	}

	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}
	
}
