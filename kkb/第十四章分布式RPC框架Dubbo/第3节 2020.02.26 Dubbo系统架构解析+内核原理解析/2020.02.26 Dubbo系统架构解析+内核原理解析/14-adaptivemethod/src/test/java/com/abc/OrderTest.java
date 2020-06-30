package com.abc;

import com.abc.spi.Order;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class OrderTest {

    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);

        Order adaptiveExtension = loader.getAdaptiveExtension();
        // 模拟一个URL
        URL url = URL.valueOf("xxx://localhost/ooo");
        System.out.println(adaptiveExtension.pay(url));
    }

    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);

        Order adaptiveExtension = loader.getAdaptiveExtension();
        // 模拟一个URL
        // 假设SPI接口是由多个单词构成(例如GoodsOrder），则这里的参数的写法是goods.order
        URL url = URL.valueOf("xxx://localhost/ooo?order=wechat");
        System.out.println(adaptiveExtension.pay(url));
        // System.out.println(adaptiveExtension.way());
    }

}
