package com.abc.controller;

import com.abc.service.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SomeController {

    @Autowired   // byType注入
    private WrapService service;

    @RequestMapping("/some/{param}")
    public String someHandler(@PathVariable("param") String word) {
        return service.wrap(word);
    }
}
