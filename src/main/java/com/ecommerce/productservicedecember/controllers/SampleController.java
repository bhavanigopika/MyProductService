package com.ecommerce.productservicedecember.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say") //This is "say type API's" and call it as identifier

public class SampleController {
    //localhost:8080/say/hello
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    //localhost:8080/say/bye
    @GetMapping("/bye")
    public String sayBye(){
        return "bye";
    }
}
