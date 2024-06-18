package com.Spring.productQuery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.productQuery.Entity.Product;

public interface ProductQueryRepo extends JpaRepository<Product, Integer>{

}
