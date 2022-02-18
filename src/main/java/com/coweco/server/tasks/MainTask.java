package com.coweco.server.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainTask {

	private static final Logger log =
			LoggerFactory.getLogger(MainTask.class);
	private static final SimpleDateFormat dateFormat =
			new SimpleDateFormat("HH:mm:ss");

	//TODO: попробуй применить cron="..." или что-то вроде
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {" + dateFormat.format(new Date())+"}");
	}
}
