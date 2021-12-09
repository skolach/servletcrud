package com.globallogic.edu.entity;

public class Route {

    private int id;
    private int orderId;
    private int pointOrder;
    private String pointName;
    
    public Route() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPointOrder() {
        return pointOrder;
    }

    public void setPointOrder(int pointOrder) {
        this.pointOrder = pointOrder;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", orderId=" + orderId + ", pointName=" + pointName + ", pointOrder=" + pointOrder
                + "]";
    }
}
