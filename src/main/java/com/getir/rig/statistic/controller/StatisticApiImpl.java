package com.getir.rig.statistic.controller;

import com.getir.rig.api.StatisticApi;
import com.getir.rig.domain.model.statistic.Statistic;
import com.getir.rig.domain.model.statistic.StatisticRES;
import com.getir.rig.domain.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StatisticApiImpl implements StatisticApi {

    private IStatisticService statisticService;

    @Autowired
    public StatisticApiImpl(IStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public ResponseEntity<StatisticRES> getOrderById(Long customerId, Date startDate, Date endDate) {
        Statistic orderStatistics = statisticService.getCustomerOrdersStatistics(customerId, startDate, endDate);

        StatisticRES statisticRES = new StatisticRES(orderStatistics);

        HttpStatus httpStatus = HttpStatus.OK;
        if (statisticRES.getData() == null) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(statisticRES, httpStatus);
    }
}
