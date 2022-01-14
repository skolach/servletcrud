package com.globallogic.edu;

import static org.assertj.core.api.Assertions.assertThat;

import com.globallogic.edu.controller.ErrorController;
import com.globallogic.edu.controller.HomeController;
import com.globallogic.edu.controller.OrderController;
import com.globallogic.edu.controller.RouteController;
import com.globallogic.edu.controller.UserController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServletCrudApplicationTest {

    @Autowired
    ErrorController errorController;
    @Autowired
    HomeController homeController;
    @Autowired
    OrderController orderController;
    @Autowired
    RouteController routeController;
    @Autowired
    UserController userController;

    @Test
    public void contextLoadsTest(){
        assertThat(errorController).isNotNull();
        assertThat(homeController).isNotNull();
        assertThat(orderController).isNotNull();
        assertThat(routeController).isNotNull();
        assertThat(userController).isNotNull();
    }

}