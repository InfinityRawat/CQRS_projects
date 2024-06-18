package com.Spring.productCommand.DTO;

import com.Spring.productCommand.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent {
	
	private String eventType;
	private Product product;
}
