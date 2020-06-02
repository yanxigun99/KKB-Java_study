package com.abc.service;


public class SomeServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        return "开课吧欢迎你，" + name;
    }
}
