package com.accolite.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.accolite.dto.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProducer {
	
	@Autowired
	private NewTopic topic;
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate; //add basedomains project as dependency to use orderevent class
	
	public void sendMessage(OrderEvent orderEvent) {
		log.info(String.format("Order event %s", orderEvent.toString()));
		
		//create a message
		Message<OrderEvent> message=MessageBuilder.withPayload(orderEvent)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		
		kafkaTemplate.send(message);
	}
	
}
