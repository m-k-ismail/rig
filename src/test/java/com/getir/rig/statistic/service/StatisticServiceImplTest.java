package com.getir.rig.statistic.service;

import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderStatus;
import com.getir.rig.domain.model.statistic.Statistic;
import com.getir.rig.domain.repository.OrderRepository;
import com.getir.rig.domain.service.IStatisticService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

    private static final Long STATISTIC_ID = 1L;
    private static final Long CUSTOMER_ID = 1L;
    private static final Long ORDER_ID = 1L;

    @Mock
    private OrderRepository orderRepository;

    private IStatisticService statisticService;

    @Before
    public void setUp() {
        statisticService = new StatisticServiceImpl(orderRepository);
    }

    @Test
    public void should_compute_customer_orders_statistics() {
        // Given
        List<Order> orders = createOrders();
        when(orderRepository.findAllByCustomerIdAndCreatedAtBetween(eq(CUSTOMER_ID), any(Date.class), any(Date.class))).thenReturn(orders);

        // When
        Statistic statistic = statisticService.getCustomerOrdersStatistics(STATISTIC_ID, new Date(), new Date());

        // Then
        Assert.assertEquals(2000, statistic.getTotalPrice(),0);
        Assert.assertEquals(2, statistic.getTotalOrderCount());
        Assert.assertEquals(2, statistic.getTotalBookCount());
    }

    private Order createOrder() {
        Order order = new Order();

        order.setTotalPrice(1000);
        order.setCreatedAt(new Date());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setCustomerId(1L);
        order.setBookIds(Arrays.asList(1L, 2L));

        return order;
    }

    private Order createOrderWithId() {
        Order order = createOrder();
        order.setId(ORDER_ID);
        return order;
    }

    private List<Order> createOrders() {
        Order firstOrder = createOrderWithId();
        Order secondOrder = createOrderWithId();

        List<Order> orders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);

        return orders;
    }
}
