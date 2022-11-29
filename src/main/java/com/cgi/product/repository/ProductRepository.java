package com.cgi.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.product.entity.Product;
import com.cgi.product.exception.ProductNotFoundException;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	//Product findById(int id) throws ProductNotFoundException ;

}
