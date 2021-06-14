package com.getir.rig.order.controller;

import com.getir.rig.domain.model.order.*;
import com.getir.rig.domain.service.IOrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.getir.rig.domain.model.order.OrderSearchResult.emptyOrderSearchResult;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderApiImplTest {

    private static final Long ORDER_ID = 1L;

    @Mock
    private IOrderService orderService;

    private OrderApiImpl orderApi;

    @Before
    public void setUp() {
        orderApi = new OrderApiImpl(orderService);
    }

    @Test
    public void should_post_book_successfully() {
        // Given
        OrderREQ orderREQ = creatBookREQ();
        when(orderService.create(orderREQ.getData())).thenReturn(orderREQ.getData());

        // When
        ResponseEntity<OrderRES> orderRESResponseEntity = orderApi.postOrder(orderREQ);

        // Then
        Assert.assertEquals(orderREQ.getData().getId(), orderRESResponseEntity.getBody().getData().getId());
        Assert.assertEquals(HttpStatus.CREATED, orderRESResponseEntity.getStatusCode());
    }

    @Test
    public void should_get_book_byId_successfully() {
        // Given
        Order order = createOrderWithId();
        OrderSearchResult orderSearchResult = new OrderSearchResult();
        orderSearchResult.addOrder(order);
        when(orderService.getOrderById(ORDER_ID)).thenReturn(orderSearchResult);

        // When
        ResponseEntity<OrderRES> orderRESResponseEntity = orderApi.getOrder(ORDER_ID);

        // Then
        Assert.assertEquals(orderSearchResult.getOrderList().get(0), orderRESResponseEntity.getBody().getData());
        Assert.assertEquals(HttpStatus.OK, orderRESResponseEntity.getStatusCode());
    }

    @Test
    public void should_not_find_any_book_byId() {
        // Given
        when(orderService.getOrderById(ORDER_ID)).thenReturn(emptyOrderSearchResult());

        // When
        ResponseEntity<OrderRES> orderRESResponseEntity = orderApi.getOrder(ORDER_ID);

        // Then
        Assert.assertNull(orderRESResponseEntity.getBody().getData());
        Assert.assertEquals(HttpStatus.NO_CONTENT, orderRESResponseEntity.getStatusCode());
    }

    @Test
    public void should_find_books_successfully() {
        // Given
        Order order = createOrderWithId();
        OrderSearchResult orderSearchResult = new OrderSearchResult();
        orderSearchResult.addOrder(order);
        when(orderService.getOrdersByStartEndDates(any(Date.class), any(Date.class))).thenReturn(orderSearchResult);

        // When
        ResponseEntity<OrdersRES> ordersRESResponseEntity = orderApi.getOrders(new Date(), new Date());

        // Then
        Assert.assertEquals(orderSearchResult.getOrderList(), ordersRESResponseEntity.getBody().getData());
        Assert.assertEquals(HttpStatus.OK, ordersRESResponseEntity.getStatusCode());
    }

    @Test
    public void should_not_find_any_books_successfully() {
        // Given
        when(orderService.getOrdersByStartEndDates(any(Date.class), any(Date.class))).thenReturn(emptyOrderSearchResult());

        // When
        ResponseEntity<OrdersRES> ordersRESResponseEntity = orderApi.getOrders(new Date(), new Date());

        // Then
        Assert.assertEquals(Collections.emptyList(), ordersRESResponseEntity.getBody().getData());
        Assert.assertEquals(HttpStatus.NO_CONTENT, ordersRESResponseEntity.getStatusCode());
    }


    private OrderREQ creatBookREQ() {
        Order order = createOrder();

        OrderREQ orderREQ = new OrderREQ();
        orderREQ.setData(order);

        return orderREQ;
    }

    private Order createOrder() {
        Order order = new Order();

        order.setTotalPrice(1000);
        order.setCreatedAt(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setCustomerId(1L);
        order.setBookIds(Arrays.asList(1L,2L));

        return order;
    }

    private Order createOrderWithId() {
        Order order = createOrder();
        order.setId(ORDER_ID);
        return order;
    }
}
