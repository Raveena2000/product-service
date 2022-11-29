package com.cgi.product.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cgi.product.entity.Product;
import com.cgi.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#getAllCart()}
     */
    @Test
    void testGetAllCart() throws Exception {
        when(productService.getAllProductList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/getAll");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#getProductById(Long)}
     */
    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product.setProductId(123L);
        product.setProductName("Product Name");
        product.setProductPrice(1L);
        when(productService.getProductById((Long) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/{id}", 123L);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"productId\":123,\"productName\":\"Product Name\",\"productPrice\":1}"));
    }

    /**
     * Method under test: {@link ProductController#getAllCart()}
     */
    @Test
    void testGetAllCart2() throws Exception {
        when(productService.getAllProductList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#saveProduct(Product)}
     */
    @Test
    void testSaveProduct() throws Exception {
        Product product = new Product();
        product.setProductId(123L);
        product.setProductName("Product Name");
        product.setProductPrice(1L);
        when(productService.saveProduct11((Product) any())).thenReturn(product);

        Product product1 = new Product();
        product1.setProductId(123L);
        product1.setProductName("Product Name");
        product1.setProductPrice(1L);
        String content = (new ObjectMapper()).writeValueAsString(product1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"productId\":123,\"productName\":\"Product Name\",\"productPrice\":1}"));
    }
}

