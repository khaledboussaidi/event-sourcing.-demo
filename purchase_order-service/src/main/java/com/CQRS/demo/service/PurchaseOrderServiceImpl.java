package com.CQRS.demo.service;

import com.CQRS.demo.domain.PurchaseOrder;
import com.CQRS.demo.repo.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return this.purchaseOrderRepository.findAll();
    }

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderRepository.save(purchaseOrder);
    }

}