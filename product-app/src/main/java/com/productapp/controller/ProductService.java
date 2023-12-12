package com.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapp.entity.Product;
import com.productapp.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product getProductInfo(Integer productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
}
