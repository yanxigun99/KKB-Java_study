package com.abc.server;

public class RpcServerStarter {
    public static void main(String[] args) throws Exception {
        RpcServer server = new RpcServer();
        server.publish("com.abc.service");
        server.start();
    }
}
