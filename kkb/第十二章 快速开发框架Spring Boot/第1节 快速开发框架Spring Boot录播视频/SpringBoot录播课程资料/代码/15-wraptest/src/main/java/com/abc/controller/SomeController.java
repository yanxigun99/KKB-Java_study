package com.abc.controller;

import com.abc.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    @Autowired
    private SomeService service;

    @GetMapping("/some/{param}")
    public String someHandler(@PathVariable("param") String word) {
        return service.wrap(word);
    }
}
