package com.getir.rig.order.controller;

import com.getir.rig.api.OrderApi;
import com.getir.rig.domain.model.order.*;
import com.getir.rig.domain.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderApiImpl implements OrderApi {

    private IOrderService orderService;

    @Autowired
    public OrderApiImpl(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderRES> postOrder(OrderREQ body) {
        Order order = orderService.create(body.getData());

        OrderRES orderRES = new OrderRES(order);

        return new ResponseEntity<>(orderRES, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderRES> getOrder(Long orderId) {
        OrderSearchResult orderSearchResult = orderService.getOrderById(orderId);

        OrderRES orderRES = new OrderRES(orderSearchResult.getOrderList().stream().findFirst().orElse(null));

        HttpStatus httpStatus = HttpStatus.OK;
        if (orderRES.getData() == null) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(orderRES, httpStatus);
    }

    @Override
    public ResponseEntity<OrdersRES> getOrders(Date startDate, Date endDate) {
        OrderSearchResult orderSearchResult = orderService.getOrdersByStartEndDates(startDate, endDate);

        OrdersRES orderRES = new OrdersRES(orderSearchResult.getOrderList());

        HttpStatus httpStatus = HttpStatus.OK;
        if (orderRES.getData().isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(orderRES, httpStatus);
    }
}
