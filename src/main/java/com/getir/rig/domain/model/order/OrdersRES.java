package com.getir.rig.domain.model.order;

import java.util.List;

public class OrdersRES {

    private List<Order> data;

    public OrdersRES(List<Order> data) {
        this.data = data;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
