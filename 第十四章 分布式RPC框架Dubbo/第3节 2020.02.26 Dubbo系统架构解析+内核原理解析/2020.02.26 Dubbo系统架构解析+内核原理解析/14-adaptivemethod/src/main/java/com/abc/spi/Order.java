package com.abc.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

// 下单接口
@SPI("alipay")
public interface Order {
    // 支付方式
    String way();

    // 支付
    @Adaptive
    String pay(URL url);
}
