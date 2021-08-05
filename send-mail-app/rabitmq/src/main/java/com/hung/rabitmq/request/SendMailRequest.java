package com.hung.rabitmq.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRequest {

	private String to;
	
	private String topic;
	
	private String content;
	
}
