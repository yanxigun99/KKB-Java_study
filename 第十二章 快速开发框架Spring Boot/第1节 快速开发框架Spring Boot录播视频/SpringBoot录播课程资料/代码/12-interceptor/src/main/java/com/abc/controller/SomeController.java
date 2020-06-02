package com.abc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    @GetMapping("/first/some")
    public String firstHandler() {
        return "first method";
    }
    @GetMapping("/second/some")
    public String secondHandler() {
        return "second method";
    }
    @GetMapping("/third/some")
    public String thirdHandler() {
        return "third method";
    }
}
