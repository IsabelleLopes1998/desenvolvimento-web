package com.br.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/{name}")
    public String helloUser(@PathVariable String name){
        return "Hello, " + name + " nice to meet!";
    }
}
