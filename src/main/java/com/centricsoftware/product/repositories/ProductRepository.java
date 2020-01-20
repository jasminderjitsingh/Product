package com.centricsoftware.product.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centricsoftware.product.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	public List<Product> findByCategoryOrderByCreatedAtDesc(String category, Pageable pageable);
}
