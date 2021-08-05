package com.hung.rabitmq.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hung.rabitmq.config.ConfigRabbitMQ;
import com.hung.rabitmq.request.OrderStatus;

@RestController
@RequestMapping("/queue")
public class QueueRest {

	@Autowired
	RabbitTemplate rbTemplate;
	
	@GetMapping("/queue")
	public String mQueue( @RequestParam String rs)
	{
//		rbTemplate.convertAndSend(ConfigRabbitMQ.EXCHANGE, ConfigRabbitMQ.ROUTE, rs);
		return "ok";
	}
	
	@PostMapping("/request_queue")
	public String mQueue(@RequestBody OrderStatus request)
	{
		rbTemplate.convertAndSend(ConfigRabbitMQ.EXCHANGE, ConfigRabbitMQ.ROUTE, request);
		return "ok";
	}
}
