package com.abc.spi.extension;

import com.abc.spi.Order;

// 微信支付下单
public class WeChatOrder implements Order {
    @Override
    public String way() {
        System.out.println("--- 微信way() ---");
        return "微信支付方式";
    }

}
