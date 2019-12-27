package org.bjc;

public class QueriesConstants {

	final public static String GET_QUERY = "select * from email_notification_data where id=?";
	final public static String GET_EMAIL_NOT_SENT = "select * from email_notification_data where email_sent=false";
	final public static String UPDATE_EMAIL_SENT_FLAG = "update EMAIL_NOTIFICATION_DATA set email_sent=? where id=?";
	final public static String GET_ALL = "select * from email_notification_data";
}
