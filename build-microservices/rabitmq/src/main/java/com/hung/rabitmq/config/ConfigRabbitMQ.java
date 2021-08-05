package com.hung.rabitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMQ {

	public final static String QUEUE = "hung_queue";
	public final static String EXCHANGE = "hung_exchange_queue";
	public final static String ROUTE = "hung_route";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	public TopicExchange TopicExchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding Binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTE);
	}

	@Bean
	public MessageConverter MessageConveter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(MessageConveter());
		return rabbitTemplate;
	}

}
