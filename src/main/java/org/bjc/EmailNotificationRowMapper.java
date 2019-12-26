package org.bjc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

public class EmailNotificationRowMapper implements RowMapper<EmailNotificationData>{

	@Override
	public EmailNotificationData mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmailNotificationData notificationData = new EmailNotificationData();
         notificationData.setId(rs.getLong("id"));
         notificationData.setToEmail(rs.getString("to_email"));
         notificationData.setFromEmail(rs.getString("from_email"));
         notificationData.setSubject(rs.getString("subject"));
         notificationData.setContent(rs.getString("content"));
         notificationData.setAttachments(rs.getString("attachments"));
         notificationData.setCc(rs.getString("cc"));
         notificationData.setBcc(rs.getString("bcc"));
         
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 2019-12-26 13:53:10
         notificationData.setSentOn(LocalDateTime.parse(rs.getString("sent_on"), formatter));
         
         notificationData.setCreatedOn(LocalDateTime.parse(rs.getString("created_on"), formatter));
         
         notificationData.setCreatedBy(rs.getString("created_by"));
         
         notificationData.setEmailSent(rs.getBoolean("email_sent"));
         
         notificationData.setPriority(rs.getInt("priority"));
         
		return notificationData;
	}

}
