package com.Spring.productCommand.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.productCommand.Entity.Product;
import com.Spring.productCommand.Services.ProductCommandService;

@RestController
@RequestMapping("/productConsumer")
public class ProductCommandController {
		
	private ProductCommandService service;

	public ProductCommandController(ProductCommandService service) {
		this.service = service;
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> saveProd(@RequestBody Product product){
		
		Product saveProduct = service.saveProduct(product);
		
		return ResponseEntity.ok(saveProduct);
	}
	
	@PutMapping("/Product")
	public ResponseEntity<?> updateProduct(@RequestBody Product prod){
		
		service.updateProduct(prod);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product Updated....");
		
	}
}
