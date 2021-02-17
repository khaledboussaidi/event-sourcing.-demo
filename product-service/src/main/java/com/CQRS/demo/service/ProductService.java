package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;
import com.CQRS.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductDto productDto);
    void  updateProduct(ProductDto productDto);
    List<Product> findAll();
}
