package com.cgi.product.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.product.entity.Product;
import com.cgi.product.exception.ProductNotFoundException;
import com.cgi.product.repository.ProductRepository;
import com.cgi.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		System.out.println(product);
		return this.productService.saveProduct11(product);
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		return this.productService.getProductById(id);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct() {
		return new ResponseEntity<List<Product>>(productService.getAllProductList(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable int id) throws ProductNotFoundException {
		System.out.println(id);
		Product pr = this.productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("PRODUCT NOT FOUND" + id));
		this.productRepository.delete(pr);
		Map<String, Boolean> map = new LinkedHashMap<String, Boolean>();
		map.put("DELETE", Boolean.TRUE);
		return ResponseEntity.ok(map);

	}

}
