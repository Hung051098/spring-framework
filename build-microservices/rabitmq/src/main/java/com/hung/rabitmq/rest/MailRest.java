package com.hung.rabitmq.rest;

import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hung.rabitmq.config.ConfigRabbitMQ;
import com.hung.rabitmq.controller.MailController;
import com.hung.rabitmq.request.SendMailRequest;


@RestController
@RequestMapping("/mail")
@CrossOrigin(maxAge = 3600)
public class MailRest {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	 
	@PostMapping("/sendemail")
	public JSONObject mQueue(@RequestBody SendMailRequest request)
	{
		rabbitTemplate.convertAndSend(ConfigRabbitMQ.EXCHANGE, ConfigRabbitMQ.ROUTE, request);
		JSONObject data = new JSONObject();
		data.put("code", 1);
		data.put("message", "success");
		return data;
	}
}
