package com.abc;

import com.abc.spi.Order;
import com.abc.spi.extension.AdaptiveOrder;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class OrderTest {

    @Test
    public void test01() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order adaptiveExtension = loader.getAdaptiveExtension();
        System.out.println(adaptiveExtension.way());
    }

    @Test
    public void test02() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order adaptiveExtension = loader.getAdaptiveExtension();
        ((AdaptiveOrder)adaptiveExtension).setOrderName("wechat");
        System.out.println(adaptiveExtension.way());
    }

}
