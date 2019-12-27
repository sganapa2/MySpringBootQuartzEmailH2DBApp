DROP TABLE IF EXISTS EMAIL_NOTIFICATION_DATA;  
CREATE TABLE EMAIL_NOTIFICATION_DATA (
  id INTEGER AUTO_INCREMENT  PRIMARY KEY,
  to_email VARCHAR(554) NOT NULL,
  from_email VARCHAR(554) NOT NULL,
  subject VARCHAR(1024) NOT NULL,
  content VARCHAR(5090) NOT NULL,
  attachments VARCHAR(5090),
  cc VARCHAR(554),
  bcc VARCHAR(554),
  sent_on VARCHAR(30),
  created_on VARCHAR(30),
  created_by VARCHAR(50),
  email_sent BOOLEAN default false,
  priority INTEGER NOT NULL
);

INSERT INTO  EMAIL_NOTIFICATION_DATA  VALUES(101, 'agunduagain@gmail.com', 'agunduagain@gmail.com', 'Email Subject', 'Email Content', '', 'agunduagain@gmail.com','agunduagain@gmail.com', '2019-12-26 13:53:10', '2019-12-26 13:53:10', 'santosh ganapa', false, 1);