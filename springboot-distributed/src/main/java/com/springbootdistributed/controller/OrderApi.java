package com.springbootdistributed.controller;

import com.springbootdistributed.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String reInventory() {
        return orderService.reInventory();
    }

    @GetMapping("/n")
    public String getInventory() {
        return "库存：" + orderService.getInventory();
    }

    @PostMapping
    public String submit(HttpServletRequest request) {
        return orderService.submitOrder(request);
    }

}