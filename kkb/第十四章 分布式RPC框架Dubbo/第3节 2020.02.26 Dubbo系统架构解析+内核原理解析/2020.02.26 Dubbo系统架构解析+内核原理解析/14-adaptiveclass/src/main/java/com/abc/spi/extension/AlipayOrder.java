package com.abc.spi.extension;

import com.abc.spi.Order;

// 支付宝支付下单
public class AlipayOrder implements Order {
    @Override
    public String way() {
        System.out.println("--- 支付宝way() ---");
        return "支付宝支付方式";
    }
}
