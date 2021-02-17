package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;
import com.CQRS.demo.domain.PurchaseOrder;
import com.CQRS.demo.repo.PurchaseOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductServiceEventHandlerImpl implements  ProductServiceEventHandler{

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    @Override
    @Transactional
    public void updateProduct(Product product) {
        List<PurchaseOrder> userOrders = this.purchaseOrderRepository.findByProductId(product.getId());
        userOrders.forEach(p -> p.setProduct(product));
        this.purchaseOrderRepository.saveAll(userOrders);
    }

    @KafkaListener(topics = "product-service-event")
    public void consume(String productStr) {
        try{
            Product product = OBJECT_MAPPER.readValue(productStr, Product.class);
            this.updateProduct(product);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
