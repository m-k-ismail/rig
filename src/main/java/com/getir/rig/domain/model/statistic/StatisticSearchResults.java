package com.getir.rig.domain.model.statistic;

import java.util.ArrayList;
import java.util.List;

public class StatisticSearchResults {
    private final List<Statistic> statisticList = new ArrayList<>();

    public void addOrder(Statistic statistic) {
        statisticList.add(statistic);
    }

    public List<Statistic> getOrderList() {
        return statisticList;
    }

    public void addAllStatistics(List<Statistic> orders) {
        this.statisticList.addAll(orders);
    }
}
