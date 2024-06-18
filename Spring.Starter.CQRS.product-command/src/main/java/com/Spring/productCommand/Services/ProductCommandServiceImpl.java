package com.Spring.productCommand.Services;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.Spring.productCommand.DTO.ProductEvent;
import com.Spring.productCommand.Entity.Product;
import com.Spring.productCommand.Repository.ProductCommandRepo;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
	
	private ProductCommandRepo repo;
	private KafkaTemplate<String, Object> temp;
	

	public ProductCommandServiceImpl(ProductCommandRepo repo, KafkaTemplate<String, Object> temp) {
		super();
		this.repo = repo;
		this.temp = temp;
	}

	@Override
	public Product saveProduct(Product product) {
		try{
			repo.save(product);
			ProductEvent event = new ProductEvent("create",product);
			CompletableFuture<SendResult<String, Object>> send = temp.send("ProductTopic",event);
			
			send.whenComplete((res,ex)-> {
					
				if(ex==null) {
					System.out.println(" Product saved in Query table.... "+ res.getRecordMetadata().serializedValueSize());
				}
				else {
					ex.printStackTrace();
				}
			});
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		
		Product prod = repo.findById(product.getId()).orElseThrow(()->new RuntimeException("Product Not Found"));
		prod.setName(product.getName());
		prod.setPrice(product.getPrice());
		prod.setQuantity(product.getQuantity());
		
		saveProduct(prod);
		ProductEvent event = new ProductEvent("update",prod);

		CompletableFuture<SendResult<String, Object>> send = temp.send("ProductTopic",event);
		send.whenComplete((res,ex)-> {
			
			if(ex==null) {
				System.out.println(" Product updated in Query table.... "+ res.getRecordMetadata().serializedValueSize());
			}
			else {
				ex.printStackTrace();
			}
		});
		
	}

}
