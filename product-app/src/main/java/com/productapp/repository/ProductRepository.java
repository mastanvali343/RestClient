package com.productapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
