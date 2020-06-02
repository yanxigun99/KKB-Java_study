package com.abc.tomcat;

import com.abc.servnet.NettyRequest;
import com.abc.servnet.NettyResponse;
import com.abc.servnet.Servnet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tomcat服务端处理器
 */
public class TomcatServerHandler extends ChannelInboundHandlerAdapter {
    private Map<String, String> classNameMap;
    private Map<String, Servnet> instanceMap;

    public TomcatServerHandler(Map<String, String> classNameMap, Map<String, Servnet> instanceMap) {
        this.classNameMap = classNameMap;
        this.instanceMap = instanceMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            // 从请求中解析出ServnetName
            String servnetName = request.uri().split("/")[1];
            Servnet servnet;

            if (instanceMap.containsKey(servnetName)) {
                servnet = instanceMap.get(servnetName);
            } else if (classNameMap.containsKey(servnetName)) {
                String className = classNameMap.get(servnetName);
                servnet = (Servnet) Class.forName(className).newInstance();
                instanceMap.put(servnetName, servnet);
            } else {
                servnet = new DefaultServnet();
            }

            NettyRequest req = new DefaultNettyRequest(request);
            NettyResponse res = new DefaultNettyResponse(request, ctx);
            if (request.method().name().equalsIgnoreCase("GET")) {
                servnet.doGet(req, res);
            } else if (request.method().name().equalsIgnoreCase("POST")) {
                servnet.doPost(req, res);
            }
        }
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
