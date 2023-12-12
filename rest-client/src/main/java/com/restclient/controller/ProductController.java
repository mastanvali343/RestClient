package com.restclient.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restclient.dto.ProductDto;

@RestController
public class ProductController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/product/{pid}")
	public ResponseEntity<ProductDto> getCurrencyConversion(@PathVariable String pid) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductDto> product = restTemplate.getForEntity("http://localhost:8080/getProductInfo/{pid}", ProductDto.class, pid);
		return product;
	}
	@GetMapping("/product/string/{pid}")
	public String getCurrencyConversionString(@PathVariable String pid) throws JsonProcessingException {
		ResponseEntity<ProductDto> product = restTemplate.getForEntity("http://localhost:8080/getProductInfo/{pid}", ProductDto.class, pid);
		return objectMapper.writeValueAsString(product.getBody());
	}
	
	@PostMapping("product/saveProduct")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto product) throws URISyntaxException{
		URI uri = new URI("http://localhost:8080/saveProduct");
		ResponseEntity<ProductDto> response = restTemplate.postForEntity(uri, product, ProductDto.class);
		return response;
	}
	
	@GetMapping("/product/type/{pid}")
	public ResponseEntity<String> getCurrencyConversionType(@PathVariable String pid) throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> product = restTemplate.getForEntity("http://localhost:8080/getProductInfo/{pid}", Object.class, pid);
		String responseJson = objectMapper.writeValueAsString(product.getBody());
		String productType = objectMapper.readTree(responseJson).get("type").asText();
		return new ResponseEntity<String>(productType,HttpStatus.OK);
	}
}
