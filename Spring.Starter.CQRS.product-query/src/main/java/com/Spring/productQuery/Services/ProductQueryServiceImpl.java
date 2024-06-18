package com.Spring.productQuery.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.Spring.productQuery.DTO.ProductEvent;
import com.Spring.productQuery.Entity.Product;
import com.Spring.productQuery.Repository.ProductQueryRepo;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
	
	private ProductQueryRepo repo;
	

	public ProductQueryServiceImpl(ProductQueryRepo repo) {
		this.repo = repo;
	}

	
	@Override
	public Product getProduct(Integer id) {
		return repo.findById(id).orElseThrow(()->new RuntimeException("Product not Found...."));
	}
	
	@KafkaListener(topics = "ProductTopic",groupId = "grp1")
	public void productListner(ProductEvent prod) {
		
		
		try {
			
			switch(prod.getEventType().toLowerCase()) {
				
				case "create":
					repo.save(prod.getProduct());
					break;
				case "update":
					
					Product product = prod.getProduct();
					Product product1 = repo.findById(product.getId()).orElseThrow(()->new RuntimeException("Product Not Found"));
					product1.setName(product.getName());
					product1.setPrice(product.getPrice());
					product1.setQuantity(product.getQuantity());
					ProductEvent event = new ProductEvent("create",product1);
					productListner(event);
					break;
			}
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
