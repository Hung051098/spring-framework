package com.hung.rabitmq.request;

import org.springframework.core.annotation.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {

    private String status;//progress,completed
    private String message;
}
