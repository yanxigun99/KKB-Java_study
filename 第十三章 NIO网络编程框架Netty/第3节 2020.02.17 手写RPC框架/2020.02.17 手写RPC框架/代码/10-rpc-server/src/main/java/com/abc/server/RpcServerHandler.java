package com.abc.server;

import com.abc.dto.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

// SimpleChannelInboundHandler：channelRead()方法msg参数会被自动释放
// ChannelInboundHandlerAdapter：channelRead()方法msg参数不会被自动释放
public class RpcServerHandler extends SimpleChannelInboundHandler<Invocation> {
    private Map<String, Object> registryMap;

    public RpcServerHandler(Map<String, Object> registryMap) {
        this.registryMap = registryMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation msg) throws Exception {
        Object result = "没有指定的提供者";
        // 判断注册表中是否存在指定名称(接口名)的服务
        if (registryMap.containsKey(msg.getClassName())) {
            // 从注册表中获取指定的提供者
            Object provider = registryMap.get(msg.getClassName());
            // 按照客户端要求进行本地方法调用
            result = provider.getClass()
                    .getMethod(msg.getMethodName(), msg.getParamTypes())
                    .invoke(provider, msg.getParamValues());
        }

        ctx.writeAndFlush(result);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
