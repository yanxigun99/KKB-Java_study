package com.abc.spi;

import org.apache.dubbo.common.extension.SPI;

// 下单接口，指定了默认扩展名
@SPI("alipay")
public interface Order {
    // 支付方式
    String way();
}
