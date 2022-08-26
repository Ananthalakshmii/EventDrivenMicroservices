package com.accolite.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.dto.Order;
import com.accolite.dto.OrderEvent;
import com.accolite.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderProducer orderProducer;
	
	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(Uuid.randomUuid().toString());
		
		OrderEvent orderEvent=new OrderEvent();
		orderEvent.setMessage("Order status is in pending state");
		orderEvent.setStatus("PENDING");
		orderEvent.setOrder(order);
		
		orderProducer.sendMessage(orderEvent);
		
		return "Order placed successfully";
	}

}
