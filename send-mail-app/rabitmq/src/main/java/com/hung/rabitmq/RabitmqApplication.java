package com.hung.rabitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hung.rabitmq.config.ConfigStartProject;

@SpringBootApplication
@EnableEurekaClient
//@EnableScheduling
public class RabitmqApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(RabitmqApplication.class, args);
//	}
	private static final Logger logger = LoggerFactory.getLogger(RabitmqApplication.class);
	public static void main(String[] args) {	
		logger.info("starting...");
		System.out.println(ConfigStartProject.getParam("spring_boot", "conf_path"));
		new SpringApplicationBuilder(RabitmqApplication.class)
		.properties("spring.config.location:" + ConfigStartProject.getParam("spring_boot", "conf_path")).build().run(args);
	}
}
