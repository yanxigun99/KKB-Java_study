package com.abc.tomcat;

import com.abc.servnet.Servnet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tomcat服务器核心
 */
public class TomcatServer {
    // key为Servnet的名称，是该Servnet类的简单类名全小写，value为该Servnet类的全限性类名
    private Map<String, String> classNameMap = new ConcurrentHashMap<>();
    // key为Servnet的名称，是该Servnet类的简单类名全小写，value为该Servnet实例
    private Map<String, Servnet> instanceMap = new ConcurrentHashMap<>();

    private String basePackage;

    public TomcatServer(String basePackage) {
        this.basePackage = basePackage;
    }

    // 启动Tomcat
    public void start() throws Exception {
        // 加载指定包中的类名
        cacheClassName(basePackage);
        // 运行Tomcat
        runServer();
    }

    // 将指定包中所有包名写入到classNameMap中
    private void cacheClassName(String basePackage) {
        // 加载指定的包为URL
        URL resource = this.getClass().getClassLoader()
                // com.abc.webapp  => com/abc/webapp
                .getResource(basePackage.replaceAll("\\.", "/"));

        // 若没有指定的资源，则直接结束
        if (resource == null) {
            return;
        }

        // 将URL资源转化为File
        File dir = new File(resource.getFile());
        // 遍历指定包及其子孙包中的所有文件，查找.class文件
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // 若当前file为目录，则递归
                cacheClassName(basePackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                // 去年.class的扩展名，获取到简单类名
                String fileName = file.getName().replace(".class", "");
                classNameMap.put(fileName.toLowerCase(), basePackage + "." + fileName);
            }
        }
        // System.out.println(classNameMap);
    }

    private void runServer() throws InterruptedException {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    // 指定存放已经连接上但还来不及处理的请求的队列的长度
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 为了保证请求长连接不被清除掉
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new TomcatServerHandler(classNameMap, instanceMap));
                        }
                    });
            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("Tomcat已启动");
            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
