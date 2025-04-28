package org.example.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dasher {

    private static final Double BASE_RATE = 0.3; // base rate not belong to any specific dasher

    public static class Delivery {
        private String deliverId;
        private Integer startTime;
        private Integer endTime; // the delivery may not be finished yet

        public Delivery(String deliverId, int startTime, int endTime) {
            this.deliverId = deliverId;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getDeliverId() {
            return deliverId;
        }

        public void setDeliverId(String deliverId) {
            this.deliverId = deliverId;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }
    }

    private String dasherId;
    private List<Delivery> deliveryList;

    public Dasher(String dasherId) {
        this.dasherId = dasherId;
        this.deliveryList = new ArrayList<>();
    }

    public void fillSampleDeliveries() { // generate sample data for testing
        deliveryList.add(new Delivery("1", 830, 900));
        deliveryList.add(new Delivery("2", 900, 915));
        deliveryList.add(new Delivery("1", 900, 915));
        deliveryList.add(new Delivery("2", 915, 945));
    }

    public double calculateDasherDeliveryAmount() {
        double totalAmount = 0;
        for (Delivery delivery: deliveryList) {
            if (Objects.isNull(delivery.startTime)
                    || Objects.isNull(delivery.endTime)
                    || delivery.startTime <= 0
                    || delivery.endTime <= 0
                    || delivery.endTime <= delivery.startTime) { // pre-check
                continue;
            }
            totalAmount += delivery.endTime - delivery.startTime;
        }
        return totalAmount * BASE_RATE;
    }

    public String getDasherId() {
        return dasherId;
    }

    public void setDasherId(String dasherId) {
        this.dasherId = dasherId;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public static void main(String[] args) {
        Dasher dasher = new Dasher("1");
        dasher.fillSampleDeliveries();
        double amt = dasher.calculateDasherDeliveryAmount();
        System.out.println("Total amount dasher with ID=1 received: " + String.format("%.2f", amt));
    }
}
