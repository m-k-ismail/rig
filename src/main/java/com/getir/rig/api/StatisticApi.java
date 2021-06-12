package com.getir.rig.api;

import com.getir.rig.domain.model.statistic.StatisticRES;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/v1/rig")
public interface StatisticApi {

    @GetMapping("/statistics/{customerId}/orders")
    ResponseEntity<StatisticRES> getOrderById(@PathVariable Long customerId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate);
}
