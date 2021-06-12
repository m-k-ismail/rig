package com.getir.rig.domain.model.customer;

public class CustomerRES {
    private Customer data;

    public CustomerRES(Customer data) {
        this.data = data;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }

}
