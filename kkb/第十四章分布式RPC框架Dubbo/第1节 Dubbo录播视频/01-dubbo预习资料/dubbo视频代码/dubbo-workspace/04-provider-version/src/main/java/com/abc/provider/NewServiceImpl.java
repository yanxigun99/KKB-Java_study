package com.abc.provider;


import com.abc.service.SomeService;

public class NewServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("执行【新】的提供者NewServiceImpl的hello() ");
        return "NewServiceImpl";
    }
}
