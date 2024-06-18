package com.Spring.productCommand.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.productCommand.Entity.Product;

public interface ProductCommandRepo extends JpaRepository<Product, Integer>{

}
