package com.globallogic.edu.entity;

import org.mapstruct.Mapper;

@Mapper
public interface RouteDtoMapper {

    RouteDto routeToRouteDto(Route rote);
    Route routeDtoToRoute(RouteDto routeDto);

}