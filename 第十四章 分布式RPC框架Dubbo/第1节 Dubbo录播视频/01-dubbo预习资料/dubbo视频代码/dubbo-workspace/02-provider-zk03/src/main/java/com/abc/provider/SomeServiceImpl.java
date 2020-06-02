package com.abc.provider;


import com.abc.service.SomeService;

public class SomeServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("执行【第三个】提供者的hello() ");
        return "【第三个】提供者";
    }
}
