package com.cgi.product.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cgi.product.entity.Product;
import com.cgi.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    /**
     * Method under test: {@link ProductService#saveProduct11(Product)}
     */
    @Test
    void testSaveProduct11() {
        Product product = new Product();
        product.setProductId(123L);
        product.setProductName("Product Name");
        product.setProductPrice(1L);
        when(productRepository.save((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setProductId(123L);
        product1.setProductName("Product Name");
        product1.setProductPrice(1L);
        assertSame(product, productService.saveProduct11(product1));
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductService#getProductById(Long)}
     */
    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setProductId(123L);
        product.setProductName("Product Name");
        product.setProductPrice(1L);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(product, productService.getProductById(123L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductService#getProductById(Long)}
     */
    @Test
    void testGetProductById2() {
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertNull(productService.getProductById(123L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductService#getAllProductList()}
     */
    @Test
    void testGetAllProductList() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> actualAllProductList = productService.getAllProductList();
        assertSame(productList, actualAllProductList);
        assertTrue(actualAllProductList.isEmpty());
        verify(productRepository).findAll();
    }
}

