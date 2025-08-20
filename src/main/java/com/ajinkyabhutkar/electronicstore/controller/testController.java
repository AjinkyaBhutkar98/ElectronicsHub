package com.ajinkyabhutkar.electronicstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/electronicstore")
public class testController {

    @GetMapping
    public String Test(){
        return "working fine";
    }
}
