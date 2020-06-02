package com.abc.provider;


import com.abc.service.SomeService;

public class SomeServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("执行【第一个】提供者的hello() ");
        return "【第一个】提供者";
    }
}
