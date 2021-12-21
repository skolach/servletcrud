package com.globallogic.edu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Timestamp createdAt;
    private Timestamp startAt;
    private Timestamp endAt;
    private BigDecimal price;
    private Integer routeDiscount;
    private Integer userDiscount;
    private BigDecimal cash;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRouteDiscount() {
        return routeDiscount;
    }

    public void setRouteDiscount(Integer routeDiscount) {
        this.routeDiscount = routeDiscount;
    }

    public Integer getUserDiscount() {
        return userDiscount;
    }

    public void setUserDiscount(Integer userDiscount) {
        this.userDiscount = userDiscount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void merge(Order another) {
        userId = another.getUserId() == null ? userId : another.getUserId();
        createdAt = another.getCreatedAt() == null ? createdAt : another.getCreatedAt();
        startAt = another.getStartAt() == null ? startAt : another.getStartAt();
        endAt = another.getEndAt() == null ? endAt : another.getEndAt();
        price = another.getPrice() == null ? price : another.getPrice();
        routeDiscount = another.getRouteDiscount() == null ? routeDiscount : another.getRouteDiscount();
        userDiscount = another.getUserDiscount() == null ? userDiscount : another.getUserDiscount();
        cash = another.getCash() == null ? cash : another.getCash();
    }

    @Override
    public String toString() {
        return "Order [cash=" + cash + ", createdAt=" + createdAt + ", endAt=" + endAt + ", id=" + id + ", price="
                + price + ", routeDiscount=" + routeDiscount + ", startAt=" + startAt + ", userDiscount=" + userDiscount
                + ", userId=" + userId + "]";
    }

}