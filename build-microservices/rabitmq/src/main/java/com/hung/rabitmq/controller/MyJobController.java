package com.hung.rabitmq.controller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJobController {
	@Scheduled(fixedDelay = 1000)
	public void scheduleFixedDelayTask() throws InterruptedException {
		System.out.println("Task1 - " + new Date());
	}

	@Scheduled(fixedRate = 2000)
	public void scheduleFixedRateTask() throws InterruptedException {
		System.out.println("Task2 - " + new Date());
	}

	@Scheduled(cron = "*/60 * * * * *")
	public void scheduleTaskUsingCronExpression() throws InterruptedException {
		System.out.println("Task3 - " + new Date());
	}
}
