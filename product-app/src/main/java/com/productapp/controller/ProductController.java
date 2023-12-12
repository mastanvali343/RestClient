package com.productapp.controller;

import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productapp.entity.Product;

import io.micrometer.common.util.internal.logging.InternalLogger;
import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;

@RestController
public class ProductController {
	private static final InternalLogger logger = Slf4JLoggerFactory.getInstance(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("saveProduct")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product dbProduct = productService.saveProduct(product);
		return new ResponseEntity<>(dbProduct,HttpStatus.CREATED);
	}
	
	@GetMapping("getProductInfo/{pid}")
	public ResponseEntity<Product> getProductInfo(@PathVariable Integer pid){
		Product product= productService.getProductInfo(pid);
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
		logger.info("getProuctInfo response : " + responseEntity.getBody());
		return responseEntity;
	}
}
