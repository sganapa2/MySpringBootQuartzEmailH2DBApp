package org.bjc;


import java.io.Serializable;
import java.time.LocalDateTime;



/**
     * A email_notification_data.
     */
    public class EmailNotificationData implements Serializable {


        private static final long serialVersionUID = 1L;

        private Long id;

        private String toEmail;

        private String fromEmail;

        private String subject;

        private String content;

        private String attachments;

        private String cc;

        private String bcc;

        private LocalDateTime sentOn;

        private LocalDateTime createdOn;

        private String createdBy;

        private Boolean emailSent;

        private Integer priority;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getToEmail() {
        return toEmail;
    }


    public EmailNotificationData setToEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }


    public String getFromEmail() {
        return fromEmail;
    }


    public EmailNotificationData setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getAttachments() {
        return attachments;
    }


    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }


    public String getCc() {
        return cc;
    }


    public void setCc(String cc) {
        this.cc = cc;
    }


    public String getBcc() {
        return bcc;
    }


    public void setBcc(String bcc) {
        this.bcc = bcc;
    }


    public LocalDateTime getSentOn() {
        return sentOn;
    }


    public void setSentOn(LocalDateTime sentOn) {
        this.sentOn = sentOn;
    }


    public LocalDateTime getCreatedOn() {
        return createdOn;
    }


    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Boolean getEmailSent() {
        return emailSent;
    }


    public void setEmailSent(Boolean emailSent) {
        this.emailSent = emailSent;
    }


    public Integer getPriority() {
        return priority;
    }


    public void setPriority(Integer priority) {
        this.priority = priority;
    }




    public EmailNotificationData toEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }


    public EmailNotificationData from(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }


    public EmailNotificationData subject(String subject) {
        this.subject = subject;
        return this;
    }


    public EmailNotificationData content(String content) {
        this.content = content;
        return this;
    }


    public EmailNotificationData attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }


    public EmailNotificationData cc(String cc) {
        this.cc = cc;
        return this;
    }


    public EmailNotificationData bcc(String bcc) {
        this.bcc = bcc;
        return this;
    }


    public EmailNotificationData sentOn(LocalDateTime sentOn) {
        this.sentOn = sentOn;
        return this;
    }


    public EmailNotificationData createdOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }


    public EmailNotificationData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }


    public EmailNotificationData emailSent(Boolean emailSent) {
        this.emailSent = emailSent;
        return this;
    }


    public EmailNotificationData priority(Integer priority) {
        this.priority = priority;
        return this;
    }


    @Override
    public String toString() {
        return "EmailNotificationData{" +
            "id=" + id +
            ", toEmail='" + toEmail + '\'' +
            ", fromEmail='" + fromEmail + '\'' +
            ", subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            ", attachments='" + attachments + '\'' +
            ", cc='" + cc + '\'' +
            ", bcc='" + bcc + '\'' +
            ", sentOn=" + sentOn +
            ", createdOn=" + createdOn +
            ", createdBy='" + createdBy + '\'' +
            ", emailSent=" + emailSent +
            ", priority=" + priority +
            '}';
    }
}