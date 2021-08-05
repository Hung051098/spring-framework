package com.hung.rabitmq.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hung.rabitmq.config.ConfigRabbitMQ;
import com.hung.rabitmq.controller.MailController;
import com.hung.rabitmq.request.OrderStatus;
import com.hung.rabitmq.request.SendMailRequest;

@Component
public class RabbitListener {
	
	@Autowired
	MailController mailController;

//	@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = Config.QUEUE)
//	public void listener(String rs)
//	{
//		System.out.println("Lắng nghe Queue: "+ rs);
//	}
	
	@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = ConfigRabbitMQ.QUEUE)
	public void listenerOrderStatus(SendMailRequest request)
	{
		try {
			System.out.println("===Request===: " + new Gson().toJson(request));
			mailController.SendMail(request.getTo(), request.getContent(), request.getTopic());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
//	@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = ConfigRabbitMQ.QUEUE)
//	public void listenerOrderStatus(OrderStatus request)
//	{
//		try {
//			System.out.println("===Request===: " + new Gson().toJson(request));
////			mailController.SendMail(request.getTo(), request.getContent(), request.getTopic());
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
