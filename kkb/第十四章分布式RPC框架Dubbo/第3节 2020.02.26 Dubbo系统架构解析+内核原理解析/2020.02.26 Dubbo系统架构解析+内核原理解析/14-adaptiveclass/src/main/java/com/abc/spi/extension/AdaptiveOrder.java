package com.abc.spi.extension;

import com.abc.spi.Order;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.util.StringUtils;

@Adaptive
public class AdaptiveOrder implements Order {

    private String orderName;

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String way() {
        ExtensionLoader<Order> loader = ExtensionLoader.getExtensionLoader(Order.class);
        Order order;
        if(StringUtils.isEmpty(orderName)) {
            order = loader.getDefaultExtension();
        } else {
            order = loader.getExtension(orderName);
        }
        return order.way();
    }
}
