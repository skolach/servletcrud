package com.globallogic.edu.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)

public class OrderDto {

    private Integer id;
    private Integer userId;
    private Timestamp createdAt;
    private Timestamp startAt;
    private Timestamp endAt;
    private BigDecimal price;
    private Integer routeDiscount;
    private Integer userDiscount;
    private BigDecimal cash;

}