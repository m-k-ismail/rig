package com.getir.rig.domain.service;

import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.customer.CustomerSearchResult;

import java.util.Optional;

public interface ICustomerService {

    Customer create(Customer customer);

    CustomerSearchResult getCustomerOrders(Long customerId, int pageOffset, int pageLimit);

    Customer getCustomer(Long customerId);

    Customer getCustomer(String email);

}
