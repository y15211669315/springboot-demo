package com.springbootjdbc.controller;

import com.springbootjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save/{size}")
    public void save(@PathVariable Integer size, HttpServletResponse response) throws IOException {
        userService.save(response, size);
    }

}
