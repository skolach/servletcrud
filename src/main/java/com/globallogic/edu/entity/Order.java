package com.globallogic.edu.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)

@Entity
@Table(name = "order_")

@DynamicUpdate @DynamicInsert

public class Order implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;
    private Integer userId;
    private Timestamp createdAt;
    private Timestamp startAt;
    private Timestamp endAt;
    private BigDecimal price;
    private Integer routeDiscount;
    private Integer userDiscount;
    private BigDecimal cash;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Set<Route> routes;

}