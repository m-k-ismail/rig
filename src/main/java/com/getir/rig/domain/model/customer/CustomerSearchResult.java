package com.getir.rig.domain.model.customer;


import java.util.ArrayList;
import java.util.List;

public class CustomerSearchResult {
    private final List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void addAllCustomers(List<Customer> customers) {
        this.customerList.addAll(customers);
    }

    public static CustomerSearchResult emptyCustomerSearchResult(){
        return new CustomerSearchResult();
    }
}
