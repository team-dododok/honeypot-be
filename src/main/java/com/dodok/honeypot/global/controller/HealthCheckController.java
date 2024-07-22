package com.dodok.honeypot.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController{

    @GetMapping
    public String healthCheck(){
        return "ʕ•ﻌ•ʔ <( Hi! )";
    }
}