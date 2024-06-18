package com.Spring.productQuery.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.productQuery.Entity.Product;
import com.Spring.productQuery.Services.ProductQueryService;

@RestController
@RequestMapping("/productQuery")
public class ProductQueryController {
		
	private ProductQueryService service;

	public ProductQueryController(ProductQueryService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id){
		Product product = service.getProduct(id);
		
			if(product==null) {
				return ResponseEntity.notFound().build();
			}
			else
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
	}
}
