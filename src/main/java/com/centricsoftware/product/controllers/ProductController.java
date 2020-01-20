package com.centricsoftware.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centricsoftware.product.domain.entities.Product;
import com.centricsoftware.product.services.ProductService;

@RestController
@RequestMapping(path = "/v1/products", produces = "application/json")
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public List<Product> searchProduct(@RequestParam("category") String category,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		return productService.searchProducts(category, pageNumber, pageSize);
	}
}
