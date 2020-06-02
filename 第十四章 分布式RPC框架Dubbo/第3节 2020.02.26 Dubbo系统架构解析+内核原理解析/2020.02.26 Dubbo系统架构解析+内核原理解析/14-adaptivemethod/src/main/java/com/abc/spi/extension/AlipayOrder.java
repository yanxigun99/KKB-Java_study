package com.abc.spi.extension;

import com.abc.spi.Order;
import org.apache.dubbo.common.URL;

// 支付宝支付下单
public class AlipayOrder implements Order {
    @Override
    public String way() {
        System.out.println("--- 支付宝way() ---");
        return "支付宝支付方式";
    }

    @Override
    public String pay(URL url) {
        System.out.println("--- 支付宝pay() ---");
        return "使用支付宝支付";
    }
}
