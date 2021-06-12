package com.getir.rig.customer.controller;

import com.getir.rig.api.CustomerApi;

import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.customer.CustomerREQ;
import com.getir.rig.domain.model.customer.CustomerRES;
import com.getir.rig.domain.model.customer.CustomerSearchResult;
import com.getir.rig.domain.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApiImpl implements CustomerApi {

    private ICustomerService customerService;

    @Autowired
    public CustomerApiImpl(ICustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerRES> postCustomer(CustomerREQ body) {
        Customer customer = customerService.create(body.getData());

        CustomerRES customerRES = new CustomerRES(customer);

        return new ResponseEntity<>(customerRES, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerRES> getCustomerWithOrders(Long customerId, int pageOffset, int pageLimit) {
        CustomerSearchResult customerSearchResult = customerService.getCustomerOrders(customerId, pageOffset, pageLimit);

        CustomerRES customerRES = new CustomerRES(customerSearchResult.getCustomerList().get(0));

        return new ResponseEntity<>(customerRES, HttpStatus.OK);
    }

}
