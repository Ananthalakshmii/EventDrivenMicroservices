package com.accolite.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.accolite.dto.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {
	
	
	@KafkaListener(
			topics = "${spring.kafka.topic.name}",
			groupId = "${spring.kafka.consumer.group-id}"
			)
	public void consume(OrderEvent orderEvent) {
		log.info(String.format("order event received in email service %s", orderEvent.toString()));
		
		//send an email to the customer
	}


}

//console:
/*

2022-08-26 12:22:44.861  INFO 8152 --- [ntainer#0-0-C-1] o.a.k.c.c.internals.ConsumerCoordinator  : 
[Consumer clientId=consumer-stock-1, groupId=stock] Setting offset for partition ordertopics-0 to the committed offset 
FetchPosition{offset=1, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[ANLA-BLR-01.accolite.com:9092 (id: 0 rack: null)], epoch=0}}

2022-08-26 12:22:44.861  INFO 8152 --- [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : stock: partitions assigned: [ordertopics-0]

2022-08-26 12:23:34.662  INFO 8152 --- [ntainer#0-0-C-1] com.accolite.kafka.OrderConsumer         : 
order event received in email service OrderEvent(message=Order status is in pending state, status=PENDING, 
order=Order(orderId=AsnO1muFTVKROB0EjTB_tg, name=laptop order, quantity=1, price=10000.0))
*/