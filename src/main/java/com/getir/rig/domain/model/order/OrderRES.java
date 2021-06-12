package com.getir.rig.domain.model.order;

public class OrderRES {
    private Order data;

    public OrderRES(Order data) {
        this.data = data;
    }

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }

}
