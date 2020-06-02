package com.abc.provider;


import com.abc.service.SomeService;

public class ZhifubaoServiceImpl implements SomeService {
    @Override
    public String hello(String name) {
        System.out.println("使用【支付宝】付款");
        return "ZhifubaoServiceImpl";
    }
}
