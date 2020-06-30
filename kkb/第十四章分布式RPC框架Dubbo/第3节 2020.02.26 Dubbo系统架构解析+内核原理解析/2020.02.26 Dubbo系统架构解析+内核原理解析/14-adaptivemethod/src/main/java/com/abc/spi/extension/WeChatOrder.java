package com.abc.spi.extension;

import com.abc.spi.Order;
import org.apache.dubbo.common.URL;

// 微信支付下单
public class WeChatOrder implements Order {
    @Override
    public String way() {
        System.out.println("--- 微信way() ---");
        return "微信支付方式";
    }

    @Override
    public String pay(URL url) {
        System.out.println("--- 微信pay() ---");
        return "使用微信支付";
    }
}
