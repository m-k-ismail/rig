package com.getir.rig.domain.repository;

import com.getir.rig.domain.model.order.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCreatedAtBetween(Date startDate, Date endDate);

    List<Order> findAllByCustomerId(Long customerId, Pageable pageable);

    List<Order> findAllByCustomerIdAndCreatedAtBetween(Long customerId, Date startDate, Date endDate);

}