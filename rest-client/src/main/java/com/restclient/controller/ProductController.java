package com.restclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

	@GetMapping("/from/{fromCurrency}")
	public String getCurrencyConversion(@PathVariable String fromCurrency) {
		RestTemplate restTemplate = new RestTemplate();
		return null;
	}
}
