package com.abc.provider;


import com.abc.service.SomeService;

public class OldServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("执行【老】的提供者OldServiceImpl的hello() ");
        return "OldServiceImpl";
    }
}
