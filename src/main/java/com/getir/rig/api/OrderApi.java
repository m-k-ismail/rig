package com.getir.rig.api;

import com.getir.rig.domain.model.order.OrderREQ;
import com.getir.rig.domain.model.order.OrderRES;
import com.getir.rig.domain.model.order.OrdersRES;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/v1/rig")
public interface OrderApi {
    @PostMapping("/orders")
    ResponseEntity<OrderRES> postOrder(@RequestBody  OrderREQ body);

    @GetMapping("/orders/{orderId}")
    ResponseEntity<OrderRES> getOrder(@PathVariable Long orderId);

    @GetMapping("/orders")
    ResponseEntity<OrdersRES> getOrders(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate);
}
