package com.abc.server;

import com.abc.registry.ZKRegistryCenter;

public class RpcServerStarter {
    public static void main(String[] args) throws Exception {
        RpcServer server = new RpcServer(new ZKRegistryCenter(), "127.0.0.1:7777");
        server.publish("com.abc.service");
        server.start();
    }
}
