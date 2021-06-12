package com.getir.rig.domain.service;

import com.getir.rig.domain.model.statistic.Statistic;

import java.util.Date;

public interface IStatisticService {

    Statistic getCustomerOrdersStatistics(Long customerId, Date startDate, Date endDate);
}
