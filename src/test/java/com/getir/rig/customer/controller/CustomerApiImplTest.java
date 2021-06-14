package com.getir.rig.customer.controller;

import com.getir.rig.api.CustomerApi;
import com.getir.rig.domain.model.customer.Customer;
import com.getir.rig.domain.model.customer.CustomerREQ;
import com.getir.rig.domain.model.customer.CustomerRES;
import com.getir.rig.domain.model.customer.CustomerSearchResult;
import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderStatus;
import com.getir.rig.domain.service.ICustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerApiImplTest {

    @Mock
    private ICustomerService customerService;

    private CustomerApi customerApi;

    @Before
    public void setUp() {
        customerApi = new CustomerApiImpl(customerService);
    }

    @Test
    public void should_post_customer_successfully() {
        // Given
        CustomerREQ customerREQ = creatCustomerREQ();
        Customer customerWitId = createCustomerWithId();
        when(customerService.create(customerREQ.getData())).thenReturn(customerWitId);

        // When
        ResponseEntity<CustomerRES> customerRESResponseEntity = customerApi.postCustomer(customerREQ);

        // Then
        Assert.assertEquals(customerWitId.getId(), customerRESResponseEntity.getBody().getData().getId());
        Assert.assertEquals(HttpStatus.CREATED, customerRESResponseEntity.getStatusCode());
    }

    @Test
    public void should_get_customer_successfully() {
        // Given
        CustomerSearchResult customerSearchResult = customerSearchResult();
        when(customerService.getCustomerOrders(1L, 0, 2)).thenReturn(customerSearchResult);

        // When
        ResponseEntity<CustomerRES> customerRESResponseEntity = customerApi.getCustomerWithOrders(1L, 0, 2);

        // Then
        Assert.assertEquals(customerSearchResult.getCustomerList().get(0), customerRESResponseEntity.getBody().getData());
        Assert.assertEquals(HttpStatus.OK, customerRESResponseEntity.getStatusCode());
    }

    private CustomerREQ creatCustomerREQ() {
        Customer customer = createCustomer();

        CustomerREQ customerREQ = new CustomerREQ();
        customerREQ.setData(customer);

        return customerREQ;
    }

    private CustomerSearchResult customerSearchResult() {
        CustomerSearchResult customerSearchResult = new CustomerSearchResult();

        customerSearchResult.addCustomer(createCustomerWithId());

        return customerSearchResult;
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
        order.setCustomerId(1L);
        order.setBookIds(Arrays.asList(1L,2L));

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        return orders;
    }

    private Customer createCustomerWithId() {
        Customer customer = createCustomer();
        customer.setId(1L);
        return customer;
    }
}
