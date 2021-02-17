package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;

public interface ProductServiceEventHandler {
    void  updateProduct(Product product);
}
