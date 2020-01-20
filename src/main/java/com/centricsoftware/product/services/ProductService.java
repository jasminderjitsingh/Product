package com.centricsoftware.product.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.centricsoftware.product.domain.entities.Product;
import com.centricsoftware.product.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		product.setCreatedAt(LocalDateTime.now());
		return productRepository.save(product);
	}

	public List<Product> searchProducts(String category, int pageNumber, int pageSize) {
		return productRepository.findByCategoryOrderByCreatedAtDesc(category, PageRequest.of(pageNumber, pageSize));
	}
}
