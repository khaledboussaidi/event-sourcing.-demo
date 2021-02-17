package com.CQRS.demo.service;

import com.CQRS.demo.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrders();
    void createPurchaseOrder(PurchaseOrder purchaseOrder);
}