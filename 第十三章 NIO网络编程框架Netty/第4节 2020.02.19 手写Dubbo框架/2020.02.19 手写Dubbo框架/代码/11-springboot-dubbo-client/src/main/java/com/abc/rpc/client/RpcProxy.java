package com.abc.rpc.client;

import com.abc.rpc.api.InvokeMessage;
import com.abc.rpc.discovery.ServiceDiscovery;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class RpcProxy {

    @Autowired
    private ServiceDiscovery serviceDiscovery;

    // 将原来的static去掉，由静态方案变为实例方法
    public <T> T create(Class<?> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                                          new Class[]{clazz},
                                          new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 若调用的是Object的方法，则直接进行本地调用
                if(Object.class.equals(method.getDeclaringClass())){
                    return method.invoke(this, args);
                }

                // 若调用的是业务接口中的方法，则进行远程调用
                return rpcInvoke(clazz, method, args);
            }
        });
    }

    // 将原来的static去掉，由静态方案变为实例方法
    public Object rpcInvoke(Class<?> clazz, Method method, Object[] args)
                                throws InterruptedException {

        String serviceAddress = serviceDiscovery.discover(clazz.getName());
        // 若zk中不存在该服务，则返回null
        if(serviceAddress == null) {
            return null;
        }

        RpcProxyHandler handler = new RpcProxyHandler();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                             ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(handler);
                        }
                    });

            String ip = serviceAddress.split(":")[0];
            String portStr = serviceAddress.split(":")[1];
            Integer port = Integer.valueOf(portStr);

            ChannelFuture future = bootstrap.connect(ip, port).sync();

            // 将调用信息传递给Netty服务端
            InvokeMessage message = new InvokeMessage();
            message.setClassName(clazz.getName());
            message.setMethodName(method.getName());
            message.setParamTypes(method.getParameterTypes());
            message.setParamValues(args);
            future.channel().writeAndFlush(message).sync();

            future.channel().closeFuture().sync();
        } finally {
            if(eventLoopGroup != null) {
                eventLoopGroup.shutdownGracefully();
            }
        }
        return handler.getResult();
    }
}
