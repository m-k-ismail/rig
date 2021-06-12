package com.getir.rig.statistic.service;

import com.getir.rig.domain.model.order.Order;
import com.getir.rig.domain.model.statistic.Statistic;
import com.getir.rig.domain.repository.OrderRepository;
import com.getir.rig.domain.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements IStatisticService {

    private OrderRepository orderRepository;

    @Autowired
    public StatisticServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Statistic getCustomerOrdersStatistics(Long customerId, Date startDate, Date endDate) {
        List<Order> orders = orderRepository.findAllByCustomerIdAndCreatedAtBetween(customerId, startDate, endDate);

        double totalPrice = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        int totalOrdersCount = orders.size();
        long totalBooksCount = orders.stream().map(Order::getBookIds).count();

        return new Statistic(totalPrice, totalOrdersCount, totalBooksCount);
    }
}
