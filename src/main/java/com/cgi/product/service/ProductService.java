package com.cgi.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.product.entity.Product;
import com.cgi.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct11(Product product) {
		// TODO Auto-generated method stub
		return this.productRepository.save(product);
	}

	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		Optional <Product> op= this.productRepository.findById(id);
		if(op.isPresent()) {
			return op.get();
	}
	else {
		return null;
	}
	
	}
	public List<Product> getAllProductList() {
		// TODO Auto-generated method stub
		
		return productRepository.findAll();
	}

	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		this.productRepository.deleteById(id);
		return true;
	}



}