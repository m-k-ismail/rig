package com.getir.rig.customer.service;

import com.getir.rig.customer.validation.CustomerValidation;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.customer.CustomerSearchResult;
import com.getir.rig.domain.repository.CustomerRepository;
import com.getir.rig.domain.service.ICustomerService;

import com.getir.rig.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;
    private CustomerValidation customerValidation;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidation customerValidation) {
        this.customerRepository = customerRepository;
        this.customerValidation = customerValidation;
    }

    @Override
    public Customer create(Customer customer) {
        customerValidation.validate(customer, getCustomer(customer.getEmail()));

        customerRepository.save(customer);

        return customer;
    }

    @Override
    public CustomerSearchResult getCustomerOrders(Long customerId, int pageOffset, int pageLimit) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new ErrorException("Customer not found. Customer id: "+ customerId);
        }

        Pageable pagination = PageRequest.of(pageOffset, pageLimit);
        List<Customer> customers = customerRepository.getCustomerWithOrdersPagination(customerId, pagination);

        CustomerSearchResult customerSearchResult = new CustomerSearchResult();

        if (customers.isEmpty()) {
            return customerSearchResult;
        }

        customerSearchResult.addCustomer(customers.get(0));

        return customerSearchResult;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public Customer getCustomer(String email) {
        return customerRepository.getCustomerByEmail(email);
    }
}
