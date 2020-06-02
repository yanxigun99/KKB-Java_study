package com.abc.rpc;

import com.abc.rpc.api.SomeService;
import com.abc.rpc.client.RpcProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.abc")
public class Application implements CommandLineRunner {

    @Autowired
    private RpcProxy proxy;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... args) {
        SomeService service = proxy.create(SomeService.class);
        if(service != null) {
            System.out.println(service.doSome("研发部"));
        }
    }
}
