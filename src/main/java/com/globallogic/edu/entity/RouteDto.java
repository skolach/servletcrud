package com.globallogic.edu.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class RouteDto {

    private int id;
    private int pointOrder;
    private String pointName;
    private Order order;

}