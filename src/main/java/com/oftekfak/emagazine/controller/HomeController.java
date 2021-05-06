package com.oftekfak.emagazine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public String mainPage(){
        return "main page";
    }

    @GetMapping(path = "/home")
    public String homePage(){
        return "home page";
    }

    @GetMapping(path = "/home1")
    public String homePage1(){
        return "1";
    }

    @GetMapping(path = "/home2")
    public String homePage2(){
        return "2";
    }

    @GetMapping(path = "/home3")
    public String homePage3(){
        return "3";
    }

    @GetMapping(path = "/home4")
    public String homePage4(){
        return "4";
    }
}
