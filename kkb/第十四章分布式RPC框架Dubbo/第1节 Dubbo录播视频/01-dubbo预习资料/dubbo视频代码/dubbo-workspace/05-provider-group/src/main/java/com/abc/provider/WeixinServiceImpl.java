package com.abc.provider;


import com.abc.service.SomeService;

public class WeixinServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("使用【微信】付款");
        return "WeixinServiceImpl";
    }
}
