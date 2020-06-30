package com.abc.rpc.server;

import com.abc.rpc.registry.RegistryCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RpcServer {

    public ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    private List<String> classCache = Collections.synchronizedList(new ArrayList<>());

    public void publish(RegistryCenter registryCenter, String serviceAddress,
                        String providerPackage) throws Exception {

        getProviderClass(providerPackage);
        doRegister(registryCenter, serviceAddress);

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(new RpcServerHandler(registryMap));
                        }
                    });

            String ip = serviceAddress.split(":")[0];
            String portStr = serviceAddress.split(":")[1];
            Integer port = Integer.valueOf(portStr);

            ChannelFuture future = bootstrap.bind(ip, port).sync();
            System.out.println("微服务已注册成功。");
            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    // 获取指定包下的所有提供者类名
    private void getProviderClass(String providerPackage) {
        URL resource = this.getClass().getClassLoader()
                .getResource(providerPackage.replaceAll("\\.", "/"));
        File dir = new File(resource.getFile());
        for (File file : dir.listFiles()) {
            if(file.isDirectory()) {
                getProviderClass(providerPackage + "." + file.getName());
            } else if(file.getName().endsWith(".class")) {
                String fileName = file.getName().replace(".class", "").trim();
                classCache.add(providerPackage + "." + fileName);
            }
        }
    }

    private void doRegister(RegistryCenter registryCenter,
                            String serviceAddress) throws Exception {
        if(classCache.size() == 0) return;

        boolean isRegisted = false;
        for (String className : classCache) {
            Class<?> clazz = Class.forName(className);
            String interfaceName = clazz.getInterfaces()[0].getName();
            registryMap.put(interfaceName, clazz.newInstance());
            if(!isRegisted) {
                registryCenter.register(interfaceName, serviceAddress);
                isRegisted = true;
            }
        }
    }
}
