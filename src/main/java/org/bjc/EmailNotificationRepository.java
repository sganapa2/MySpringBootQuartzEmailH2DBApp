package org.bjc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailNotificationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<EmailNotificationData> findById(long id) {

 /* return jdbcTemplate.queryForObject(
		  "select * from email_notification_data where id=?", new Object[] { id },
            new BeanPropertyRowMapper < EmailNotificationData > (EmailNotificationData.class));*/
    	
    	 return (List<EmailNotificationData>) jdbcTemplate.query( QueriesConstants.GET_QUERY
    			  , new Object[] { id }, new EmailNotificationRowMapper ());
    }
    
    public List < EmailNotificationData > findAll() {
	    return jdbcTemplate.query("select * from email_notification_data", new EmailNotificationRowMapper());
	}
    
	public int deleteById(long id) {
		return jdbcTemplate.update("delete from EMAIL_NOTIFICATION_DATA where id=?", new Object[] { id });
	}

	public int insert(EmailNotificationData emailData) {
		return jdbcTemplate.update(
				"INSERT INTO  EMAIL_NOTIFICATION_DATA ("
						+ " to_email, from_email, subject, content, attachments, cc, bcc, sent_on, created_on, "
						+ "created_by, email_sent, priority) " + "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {  emailData.getToEmail(), emailData.getFromEmail(),
						emailData.getSubject(), emailData.getContent(), emailData.getAttachments(), emailData.getCc(),
						emailData.getBcc(), emailData.getSentOn(), emailData.getCreatedOn(), emailData.getCreatedBy(),
						emailData.getEmailSent(), emailData.getPriority() });
	}
}
