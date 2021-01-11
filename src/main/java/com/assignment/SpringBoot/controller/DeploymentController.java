package com.assignment.SpringBoot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Display")
public class DeploymentController {

    @GetMapping(value = "/view")
    public String viewCart() {
        return "GET API - Application is running";
    }
}
