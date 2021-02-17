package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;
import com.CQRS.demo.dto.ProductDto;
import com.CQRS.demo.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements  ProductService{

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    ProductRepository productRepository;


    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;
    
    
    @Override
    public Long createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
       return  this.productRepository.save(product).getId();
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) {
        this.productRepository.findById(productDto.getId())
                .ifPresent(product -> {
                    product.setDescription(productDto.getDescription());
                this.raiseEvent(productDto);

                });

        
    }

    @Override
    public List<Product> findAll() {
        return  this.productRepository.findAll();
    }



    private void raiseEvent(ProductDto dto){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(dto);
            this.kafkaTemplate.sendDefault(dto.getId(), value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
