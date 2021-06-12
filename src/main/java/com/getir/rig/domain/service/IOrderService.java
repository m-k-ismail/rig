package com.getir.rig.domain.service;

import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.order.OrderSearchResult;

import java.util.Date;

public interface IOrderService {

    Order create(Order order);

    OrderSearchResult getOrderById(Long id);

    OrderSearchResult getOrdersByStartEndDates(Date startDate, Date endDate);

    OrderSearchResult getOrdersByCustomerId(Long customerId, int pageLimit, int pageOffset);
}
