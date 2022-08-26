package com.accolite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent{ // used for DataTransfer between producer and consumer
	
	private String message;
	private String status;
	private Order order;

}
 