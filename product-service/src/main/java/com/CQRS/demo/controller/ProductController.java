package com.CQRS.demo.controller;


import com.CQRS.demo.domain.Product;
import com.CQRS.demo.dto.ProductDto;
import com.CQRS.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public Long createProduct(@RequestBody ProductDto productDto){
        return this.productService.createProduct(productDto);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductDto productDto){

        this.productService.updateProduct(productDto);
    }

    @GetMapping("/all")
    List<Product> getAll(){
        return  productService.findAll();
    }
}
