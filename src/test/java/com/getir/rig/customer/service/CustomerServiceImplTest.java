package com.getir.rig.customer.service;


import com.getir.rig.customer.validation.CustomerValidator;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.customer.CustomerSearchResult;
import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderStatus;
import com.getir.rig.domain.repository.CustomerRepository;
import com.getir.rig.domain.service.ICustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static final Long CUSTOMER_ID = 1L;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerValidator customerValidator;

    private ICustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, customerValidator);
    }

    @Test
    public void should_create_customer_successfully() {
        // Given
        Customer customer = createCustomer();
        Customer customerWitId = createCustomerWithId();
        when(customerRepository.save(customer)).thenReturn(customerWitId);
        when(customerRepository.getCustomerByEmail(customer.getEmail())).thenReturn(null);

        // When
        Customer customerOutput = customerService.create(customer);

        // Then
        Assert.assertEquals(customerWitId.getId(), customerOutput.getId());
    }

    @Test
    public void should_find_customer_with_orders_successfully() {
        // Given
        Customer customerWitId = createCustomerWithId();
        List<Customer> customersWitOrdersResult = new ArrayList<>();
        customersWitOrdersResult.add(customerWitId);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerWitId));
        when(customerRepository.getCustomerWithOrdersPagination(customerWitId.getId(), PageRequest.of(0, 2))).thenReturn(customersWitOrdersResult);

        // When
        CustomerSearchResult customerSearchResult = customerService.getCustomerOrders(CUSTOMER_ID, 0, 2);

        // Then
        Assert.assertFalse(customerSearchResult.getCustomerList().isEmpty());
    }


    @Test
    public void should_find_customer_by_email_successfully() {
        // Given
        Customer customer = createCustomer();
        Customer customerWitId = createCustomerWithId();
        when(customerRepository.getCustomerByEmail(customer.getEmail())).thenReturn(customerWitId);

        // When
        Customer customerOutput = customerService.getCustomer(customer.getEmail());

        // Then
        Assert.assertEquals(customerWitId.getId(), customerOutput.getId());
    }

    private Customer createCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("First Name");
        customer.setLastName("Last Name");
        customer.setAddress("Istanbul");
        customer.setEmail("test@gmail.com");
        customer.addOrders(createOrders());

        return customer;
    }

    private List<Order> createOrders() {
        Order order = new Order();

        order.setTotalPrice(1000);
        order.setCreatedAt(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setCustomerId(CUSTOMER_ID);
        order.setBookIds(Arrays.asList(1L, 2L));

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        return orders;
    }

    private Customer createCustomerWithId() {
        Customer customer = createCustomer();
        customer.setId(CUSTOMER_ID);
        return customer;
    }
}