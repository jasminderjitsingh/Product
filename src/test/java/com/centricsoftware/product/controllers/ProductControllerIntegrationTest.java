package com.centricsoftware.product.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.centricsoftware.product.ProductApplication;
import com.centricsoftware.product.domain.entities.Product;
import com.centricsoftware.product.repositories.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class ProductControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addProduct_addedSuccessfully() throws Exception {
		String url = "/v1/products";
		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "\"name\": \"blue Shirt\",\r\n" + "\"description\": \"Red hugo boss shirt\",\r\n"
						+ "\"brand\": \"Hugo Boss\",\r\n" + "\"tags\": [\r\n" + "\"red\",\r\n" + "\"shirt\",\r\n"
						+ "\"slim fit\"\r\n" + "],\r\n" + "\"category\": \"apparel\"\r\n" + "}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("blue Shirt"));

	}

	@Test
	public void searchProduct_searchedSuccessfully() throws Exception {

		productRepository.save(new Product("b6afac37-cf9a-4fd4-8257-f096dbb5d34d", "red shirt", "Red hugo boss shirt",
				"Hugo Boss", Arrays.asList("red", "shirt", "slim fit"), "apparel", LocalDateTime.now()));
		productRepository
				.save(new Product("b6afac37-cf9a-4fd4-8257-f096dbb5d34d", "orange shirt", "orange hugo boss shirt",
						"Hugo Boss", Arrays.asList("orange", "shirt", "slim fit"), "apparel", LocalDateTime.now()));
		Product newestProduct = productRepository
				.save(new Product("357cd2c8-6f69-4bea-a6fa-86e40af0d867", "green shirt", "Green hugo boss shirt",
						"Hugo Boss", Arrays.asList("green", "shirt", "slim fit"), "apparel", LocalDateTime.now()));

		mockMvc.perform(get("/v1/products/search?category=apparel&pageNumber=0&pageSize=2")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value(newestProduct.getName()))
				.andExpect(jsonPath("$.size()").value(2));

	}

}
