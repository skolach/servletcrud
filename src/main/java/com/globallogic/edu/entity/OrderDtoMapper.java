package com.globallogic.edu.entity;

import org.mapstruct.Mapper;

@Mapper
public interface OrderDtoMapper {

    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);

}