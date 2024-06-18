package com.Spring.productCommand.Services;

import com.Spring.productCommand.Entity.Product;

public interface ProductCommandService {
		
	public Product saveProduct(Product product);
	
	public void updateProduct(Product product);
}
