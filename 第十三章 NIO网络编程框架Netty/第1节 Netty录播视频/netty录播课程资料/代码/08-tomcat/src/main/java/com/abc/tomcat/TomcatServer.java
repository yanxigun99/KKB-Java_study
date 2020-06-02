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
 * tomcat 服务器核心
 */

public class TomcatServer {
    //key为servnet的名称，是该servnet类简单类名全小写，value为该servnet类的全限性类名
    private Map<String,String> classNameMap=new ConcurrentHashMap<>();
    //key为servnet的名称，是该servnet类简单类名全小写，value为该servnet的实例
    private Map<String, Servnet> instanceMap=new ConcurrentHashMap<>();
    private String basePackage;

    public TomcatServer(String basePackage) {
        this.basePackage = basePackage;
    }
    //启动tomcat
    public void start(){
    //加载指定包中的类名
        cacheClassName(basePackage);
        //运行tomcat
        runServer();
}

    private void runServer() {
        EventLoopGroup parentGroup=new NioEventLoopGroup();
        EventLoopGroup childGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline= ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(null);
                        }
                    });
            ChannelFuture f=bootstrap.bind(8888).sync();
            System.out.println("tomcat已经启动");
            f.channel().closeFuture().sync();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
    //将指定包中名写入到classNameMap中 com.abc.webapp
    private void cacheClassName(String basePackage) {
        //加载指定的包为URL
        URL resource = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));
        //将URL资源转化为file
        File dir = new File(resource.getFile());
        //遍历指定包及子孙包中的所有文件，查找.calss文件
        for (File file : dir.listFiles()) {
            if(file.isDirectory()){
                //当前file为目录，则递归
                cacheClassName(basePackage+"."+file.getName());
            }else if(file.getName().endsWith(".class")){
                String fileName=file.getName().replace(".class","");
                classNameMap.put(fileName.toLowerCase(),basePackage+"."+file.getName());
            }
        }

    }
    }
