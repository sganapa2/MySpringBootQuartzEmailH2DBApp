package org.bjc;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailJob implements Job
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	EmailNotificationRepository repository;
	

	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		System.out.println("Hello Quartz!");
		
		List<EmailNotificationData> lstEmailNotificationData = fetchDataToSendEmail();
		//repository = (EmailNotificationRepository) context.getJobDetail().getJobDataMap().get("repoObj");
		//List<EmailNotificationData> lstEmailNotificationData = repository.findAll();
		
		//List<EmailNotificationData> lstEmailNotificationData = (List<EmailNotificationData>) context.getJobDetail().getJobDataMap().get("lstEmailNotificationData");
		
		for(EmailNotificationData notification : lstEmailNotificationData) {
			try {
				System.out.println(notification);
			if(!notification.getEmailSent()) {
				//send an email
				//update DB to set an email sent as true
				System.out.println("Sending an email and updating db to set to email_sent=true");
				if(sendEmail(notification)) {
					updateDbRecord(notification.getId(), true);
				}
			} else {
				System.out.println("Skipping to send an email due to alredy set email_sent=true");
			}
			} catch (Exception e) {
				//update DB to set an email sent as false due to failure
				System.out.println("Sending an email and updating db to set to email_sent=false");
				updateDbRecord(notification.getId(), false);
			}
		}
	}
	
	public void updateDbRecord(long id, boolean emailSentFlag) {
		logger.info("UPDATING email_sent flag for id:" + id + " AND emailSentFlag:" + emailSentFlag);
		repository.updateEmailSentFlag(id, emailSentFlag);
	}
	
	public List<EmailNotificationData> fetchDataToSendEmail() {
		
		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 2019-12-26T13:53:10

		EmailNotificationData notificationData = new EmailNotificationData();
		notificationData.setId(1L);
		notificationData.setToEmail("agunduagain@gmail.com");
		notificationData.setFromEmail("agunduagain@gmail.com");
		notificationData.setSubject("subject");
		notificationData.setContent("content");
		notificationData.setAttachments("attachment");
		notificationData.setCc("agunduagain@gmail.com");
		notificationData.setBcc("agunduagain@gmail.com");

		notificationData.setSentOn(LocalDateTime.parse("2019-12-26 13:53:10", formatter));

		notificationData.setCreatedOn(LocalDateTime.parse("2019-12-26 13:53:10", formatter));

		notificationData.setCreatedBy("Created by santosh ganapa");

		notificationData.setEmailSent(false);

		notificationData.setPriority(1);

		if(repository == null) {
			System.out.println("repository is NULL========================");
		}
		if(repository.findById(notificationData.getId()).isEmpty()) {
			logger.info("Inserting -> {}", repository.insert(notificationData));
		}	*/	
		return  repository.findAllWithNoStatus();
	}
	
	public boolean sendEmail(EmailNotificationData emailData) {
		boolean emailSentStatus = false;
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emailData.getToEmail());
			msg.setSubject(emailData.getSubject());
			msg.setText(emailData.getContent());
			msg.setFrom(emailData.getFromEmail());
			msg.setCc(emailData.getCc());
			msg.setBcc(emailData.getBcc());
			javaMailSender.send(msg);
			emailSentStatus = true;
		} catch (MailException mexc) {
			emailSentStatus = false;
			logger.error(mexc.getMessage());
		}
		return emailSentStatus;
	}

	/**
	 * 
	 * @param emailData
	 * @throws MessagingException
	 * @throws IOException
	 */
	void sendEmailWithAttachment(EmailNotificationData emailData) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(emailData.getToEmail());

        helper.setSubject(emailData.getSubject());

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);
    }
	
}