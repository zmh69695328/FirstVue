package com.zmh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class HelloController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}