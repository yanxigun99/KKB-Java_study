package com.abc.rpc;

import com.abc.rpc.registry.RegistryCenter;
import com.abc.rpc.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.abc")
public class Application implements CommandLineRunner {
    @Autowired
    private RpcServer server;
    @Autowired
    RegistryCenter registryCenter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        String serviceAddress = "127.0.0.1:8888";
        String providerPackage = "com.abc.rpc.provider";
        server.publish(registryCenter, serviceAddress, providerPackage);
    }
}
