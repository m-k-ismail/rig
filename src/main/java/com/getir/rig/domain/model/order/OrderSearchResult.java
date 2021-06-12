package com.getir.rig.domain.model.order;

import java.util.ArrayList;
import java.util.List;

public class OrderSearchResult {
    private final List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addAllOrders(List<Order> orders) {
        this.orderList.addAll(orders);
    }
}
