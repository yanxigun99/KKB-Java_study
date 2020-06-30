package com.abc.consumer;


import com.abc.client.RpcProxy;
import com.abc.service.SomeService;

public class RpcConsumer {

    public static void main(String[] args) {
        SomeService service = RpcProxy.create(SomeService.class);
        System.out.println(service.hello("tom"));
        System.out.println(service.hashCode());
    }
}
