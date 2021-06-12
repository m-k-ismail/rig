package com.getir.rig.domain.model.statistic;

public class Statistic {

    private double totalPrice;
    private int totalOrderCount;
    private long totalBookCount;

    public Statistic(double totalPrice, int totalOrderCount, long totalBookCount) {
        this.totalPrice = totalPrice;
        this.totalOrderCount = totalOrderCount;
        this.totalBookCount = totalBookCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public long getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }
}
