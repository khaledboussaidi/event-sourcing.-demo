package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;
import com.CQRS.demo.domain.PurchaseOrder;
import com.CQRS.demo.domain.User;
import com.CQRS.demo.repo.PurchaseOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @KafkaListener(topics = "user-service-event")
    public void consume(String userStr) {
        try{
            User user = OBJECT_MAPPER.readValue(userStr, User.class);
            this.updateUser(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        List<PurchaseOrder> userOrders = this.purchaseOrderRepository.findByUserId(user.getId());
        userOrders.forEach(p -> p.setUser(user));
        this.purchaseOrderRepository.saveAll(userOrders);
    }
}