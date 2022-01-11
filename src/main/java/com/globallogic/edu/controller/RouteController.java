package com.globallogic.edu.controller;

import static com.globallogic.edu.configuration.Constant.ROLE_ADMIN;
import static com.globallogic.edu.configuration.Constant.ROLE_USER;
import com.globallogic.edu.entity.RouteDto;
import com.globallogic.edu.entity.RouteDtoMapper;
import com.globallogic.edu.service.RouteService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    private RouteDtoMapper routeDtoMapper = Mappers.getMapper(RouteDtoMapper.class);

    @Secured({ROLE_USER, ROLE_ADMIN})
    @GetMapping("/{id}")
    public String editRoute(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("route", routeService.getById(id));
        return "RouteView";
    }

    @Secured(ROLE_ADMIN)
    @PostMapping
    public String saveRoute(RouteDto routeDto){
        routeService.save(routeDtoMapper.routeDtoToRoute(routeDto));
        return "redirect:/order/" + routeDto.getOrder().getId();
    }

    @Secured(ROLE_ADMIN)    
    @GetMapping("delete/{id}")
    public String deleteRoute(@PathVariable("id") Integer id) {
        Integer orderId = routeService.getById(id).getOrder().getId();
        routeService.deleteById(id);
        return "redirect:/order/" + orderId;
    }
}
