package org.bjc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot2JdbcWithH2Application implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmailNotificationRepository repository;
	
	@Autowired
	Scheduler scheduler;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SpringBoot2JdbcWithH2Application.class, args);

		//Thread.sleep(100);
		// Quartz 1.6.3
		// JobDetail job = new JobDetail();
		// job.setName("dummyJobName");
		// job.setJobClass(HelloJob.class);
		/*JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("dummyJobName", "group1").build();*/
        //job.getJobDataMap().put("repoObj", repository);
		// Quartz 1.6.3
		// CronTrigger trigger = new CronTrigger();
		// trigger.setName("dummyTriggerName");
		// trigger.setCronExpression("0/5 * * * * ?");

/*		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
*/
	}

	// For commandliner interface
	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 2019-12-26T13:53:10

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

		logger.info("Inserting -> {}", repository.insert(notificationData));
		// logger.info("Update 10001 -> {}", repository.update(new Student(10001
		// L, "Name-Updated", "New-Passport")));

		// repository.deleteById(1);
		
		logger.info("EmailNotificationId 1 -> {}", repository.findById(1));

		List<EmailNotificationData> lstEmailNotificationData = repository.findAll();
		
		for(EmailNotificationData notification : lstEmailNotificationData) {
			try {
			if(!notification.getEmailSent()) {
				//send an email
				//update DB to set an email sent as true
			}
			} catch (Exception e) {
				//update DB to set an email sent as false due to failure
			}
		}
		
		
		logger.info("All users 2 -> {}", lstEmailNotificationData);
		
		JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("dummyJobName", "group1").build();
		//job.getJobDataMap().put("repoObj", repository);
		
		job.getJobDataMap().put("majakey", "majakey->chi->value");
		
		//List<EmailNotificationData> lstEmailNotificationData = repository.findAll();
		//job.getJobDataMap().put("lstEmailNotificationData", lstEmailNotificationData);
		
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();

		// schedule it
//		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		

		
	}
}